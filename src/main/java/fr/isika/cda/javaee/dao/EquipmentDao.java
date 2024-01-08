package fr.isika.cda.javaee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.presentation.viewmodel.EquipmentViewModel;

@Stateless
public class EquipmentDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private ActivityDao activityDao;

	public Equipment createEquipment (EquipmentViewModel equipmentViewModel, List<Long> activitiesIdsForGivenEquipement) {
		Equipment equipmentBean = new Equipment();
		equipmentBean.setEquipmentName(equipmentViewModel.getEquipmentName());
		equipmentBean.setQuantity(equipmentViewModel.getQuantity());
		equipmentBean.setCondition(equipmentViewModel.getCondition());
		equipmentBean.setDetails(equipmentViewModel.getDetails());
		//equipmentBean.setActivityList(equipmentViewModel.getActivityList());
		associateEquipementWithActivities(activitiesIdsForGivenEquipement, equipmentBean);
		entityManager.persist(equipmentBean);
		entityManager.flush();
		return equipmentBean;
	}

	public List<Activity> getAllActivitiesWithEquipements() {
		return entityManager.createQuery("SELECT act FROM Activity act LEFT JOIN FETCH act.equipmentList", Activity.class).getResultList();

	}
	
	private void associateEquipementWithActivities(List<Long> activitiesIds, Equipment equipmentBean) {
		List<Activity> associated = new ArrayList<Activity>();
		
		activitiesIds
			.stream()
			.map(id -> activityDao.findActivityById(id))
			.forEach(activity -> {
				activity.getEquipmentList().add(equipmentBean);
				associated.add(activity);
			});
		
		// associer l'quipement courant aux  activites (lien inverse) 
		equipmentBean.setActivityList(associated);
	}
	
	//SEAK IN DATABASE EQUIPEMENT BY ID
		public Equipment findEquipmentById(long equipmentId) {
			return entityManager
					.createQuery("SELECT equi FROM Equipment equi WHERE equi.id = :equipementIdParam", Equipment.class)
					.setParameter("equipementIdParam", equipmentId)
					.getSingleResult();
		}
}

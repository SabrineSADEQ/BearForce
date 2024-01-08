package fr.isika.cda.javaee.dao;

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
	

	
	public Equipment createEquipment (EquipmentViewModel equipmentViewModel, Long selectedActivity) {
		Equipment equipmentBean = new Equipment();
		//Activity act = findActivityById(equipmentViewModel.getActivityId());
		//equipmentBean.setActivity(act);
		equipmentBean.setEquipmentName(equipmentViewModel.getEquipmentName());
		equipmentBean.setQuantity(equipmentViewModel.getQuantity());
		equipmentBean.setCondition(equipmentViewModel.getCondition());
		equipmentBean.setDetails(equipmentViewModel.getDetails());
		Activity activity = activityDao.findActivityById(selectedActivity);
		equipmentBean.setActivity(activity);
		
		//equipmentBean.setActivityList(equipmentViewModel.getActivityList());
		//associateEquipementWithActivities(activitiesIdsForGivenEquipement, equipmentBean);
		entityManager.persist(equipmentBean);
		entityManager.flush();
		return equipmentBean;
	}
	
	public List<Equipment> getAllEquipmentsWithActivities() {
		//return entityManager.createQuery("SELECT equi FROM Equipment WHERE activity_id = null equi LEFT JOIN FETCH equi.activity ", Equipment.class).getResultList();
		 return entityManager.createQuery("SELECT equi FROM Equipment equi LEFT JOIN FETCH equi.activity WHERE equi.activity IS NULL", Equipment.class)
		            .getResultList();
	}
	
//	private void associateEquipementWithActivities(List<Long> activitiesIds, Equipment equipmentBean) {
//		List<Activity> associated = new ArrayList<Activity>();
//		
//		activitiesIds
//			.stream()
//			.map(id -> activityDao.findActivityById(id))
//			.forEach(activity -> {
//				activity.getEquipmentList().add(equipmentBean);
//				associated.add(activity);
//			});
//		
//		// associer l'quipement courant aux  activites (lien inverse) 
//		equipmentBean.setActivityList(associated);
//	}
	
	//SEAK IN DATABASE EQUIPEMENT BY ID
		public Equipment findEquipmentById(long equipmentId) {
			return entityManager
					.createQuery("SELECT equi FROM Equipment equi WHERE equi.id = :equipementIdParam", Equipment.class)
					.setParameter("equipementIdParam", equipmentId)
					.getSingleResult();
		}
}

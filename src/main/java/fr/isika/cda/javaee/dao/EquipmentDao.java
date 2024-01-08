package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.presentation.viewmodel.EquipmentViewModel;

@Stateless
public class EquipmentDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Equipment createEquipment (EquipmentViewModel equipmentViewModel) {
		Equipment equipmentBean = new Equipment();
		System.out.println(equipmentViewModel.getActivityId());
		//Activity act = findActivityById(equipmentViewModel.getActivityId());
		//equipmentBean.setActivity(act);
		equipmentBean.setEquipmentName(equipmentViewModel.getEquipmentName());
		equipmentBean.setQuantity(equipmentViewModel.getQuantity());
		equipmentBean.setCondition(equipmentViewModel.getCondition());
		equipmentBean.setDetails(equipmentViewModel.getDetails());
		//equipmentBean.setActivityList(equipmentViewModel.getActivityList());
		//associateEquipementWithActivities(activitiesIdsForGivenEquipement, equipmentBean);
		entityManager.persist(equipmentBean);
		entityManager.flush();
		return equipmentBean;
	}
	
	public List<Equipment> getAllEquipmentsWithActivities() {
		return entityManager.createQuery("SELECT equi FROM Equipment equi LEFT JOIN FETCH equi.activity", Equipment.class).getResultList();
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

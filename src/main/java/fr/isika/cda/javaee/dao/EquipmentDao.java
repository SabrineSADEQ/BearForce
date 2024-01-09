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
		equipmentBean.setEquipmentName(equipmentViewModel.getEquipmentName());
		equipmentBean.setQuantity(equipmentViewModel.getQuantity());
		equipmentBean.setCondition(equipmentViewModel.getCondition());
		equipmentBean.setDetails(equipmentViewModel.getDetails());
		Activity activity = activityDao.findActivityById(selectedActivity);
		equipmentBean.setActivity(activity);
		entityManager.persist(equipmentBean);
		entityManager.flush();
		return equipmentBean;
	}
	
	public void updateEquipment(Equipment updateEquipment, Long selectedActivity) {
		Equipment existingEquipment = entityManager.find(Equipment.class, updateEquipment.getId());
		existingEquipment.setEquipmentName(updateEquipment.getEquipmentName());
		existingEquipment.setDetails(updateEquipment.getDetails());
		existingEquipment.setQuantity(updateEquipment.getQuantity());
		existingEquipment.setCondition(updateEquipment.getCondition());
		Activity activity = activityDao.findActivityById(selectedActivity);
		existingEquipment.setActivity(activity);
		entityManager.merge(existingEquipment);
	}
	
	public void deleteEquipment(Long equipmentToDeleteId) {
		Equipment equipmentToDelete = entityManager
				.createQuery("SELECT e FROM Equipment e LEFT JOIN FETCH e.activity WHERE e.id = :equipmentIdParam", Equipment.class)
				.setParameter("equipmentIdParam", equipmentToDeleteId)
				.getSingleResult();
		entityManager.remove(equipmentToDelete);
	}
	
	public List<Equipment> getAllUnavailableEquipmentsWithActivities() {
		 return entityManager.createQuery("SELECT e FROM Equipment e LEFT JOIN FETCH e.activity WHERE e.activity IS NULL", Equipment.class)
		            .getResultList();
	}
	
	public List<Equipment> getAllEquipmentsWithActivities() {
		return entityManager
				.createQuery("SELECT e FROM Equipment e LEFT JOIN FETCH e.activity", Equipment.class)
				.getResultList();
	}
	
	//SEAK IN DATABASE EQUIPEMENT BY ID
		public Equipment findEquipmentById(long equipmentId) {
			return entityManager
					.createQuery("SELECT equi FROM Equipment equi WHERE equi.id = :equipementIdParam", Equipment.class)
					.setParameter("equipementIdParam", equipmentId)
					.getSingleResult();
		}
}

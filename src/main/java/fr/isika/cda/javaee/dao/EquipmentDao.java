package fr.isika.cda.javaee.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.account.controller.LoginController;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.presentation.viewmodel.EquipmentViewModel;

@Stateless
public class EquipmentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private ActivityDao activityDao;
	
	@Inject
	private SpaceDao spaceDao;

	public Long persist(Equipment equipment) {
		entityManager.persist(equipment);
		return equipment.getId();
	}

	private Long getCurrentConnectedGymId() { 
		LoginController controller = new LoginController();
		Account logged = controller.getLoggedAccount();
		return logged.getGymId();
		}
	
	public Equipment createEquipment(EquipmentViewModel equipmentViewModel, Long selectedActivity) {
		Equipment equipmentBean = new Equipment();
		equipmentBean.setEquipmentName(equipmentViewModel.getEquipmentName());
		equipmentBean.setQuantity(equipmentViewModel.getQuantity());
		equipmentBean.setCondition(equipmentViewModel.getCondition());
		equipmentBean.setDetails(equipmentViewModel.getDetails());
		equipmentBean.setAttachedGymId(getCurrentConnectedGymId());
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
		existingEquipment.setAttachedGymId(getCurrentConnectedGymId());
		Activity activity = activityDao.findActivityById(selectedActivity);
		existingEquipment.setActivity(activity);
		entityManager.merge(existingEquipment);
	}

	public void deleteEquipment(Long equipmentToDeleteId) {
		Equipment equipmentToDelete = entityManager
				.createQuery("SELECT e FROM Equipment e LEFT JOIN FETCH e.activity WHERE e.id = :equipmentIdParam",
						Equipment.class)
				.setParameter("equipmentIdParam", equipmentToDeleteId).getSingleResult();
		entityManager.remove(equipmentToDelete);
	}

	public List<Equipment> getAllUnavailableEquipmentsWithActivities() {
		return entityManager
				.createQuery("SELECT e FROM Equipment e LEFT JOIN FETCH e.activity WHERE e.activity IS NULL",
						Equipment.class)
				.getResultList();
	}

	public List<Equipment> getAllEquipmentsWithActivities() {
		LoginController controller = new LoginController();
		Account logged = controller.getLoggedAccount();
		Long loggedAccountGymId = logged.getGymId();
		return entityManager.createQuery("SELECT e FROM Equipment e LEFT JOIN FETCH e.activity WHERE e.attachedGymId = :gymIdParam", Equipment.class)
				.setParameter("gymIdParam", loggedAccountGymId)
				.getResultList();
	}

	// SEAK IN DATABASE EQUIPEMENT BY ID
	public Equipment findEquipmentById(long equipmentId) {
		return entityManager
				.createQuery("SELECT equi FROM Equipment equi WHERE equi.id = :equipementIdParam", Equipment.class)
				.setParameter("equipementIdParam", equipmentId).getSingleResult();
	}
	
	public List<Equipment> getAllEquipmentsOfGymId() throws Exception {
		Long currentGymId = getLoadedSpaceId();	
		return entityManager
				.createQuery("SELECT equi FROM Equipment equi WHERE equi.attachedGymId = :gymIdParam"
						, Equipment.class)
				.setParameter("gymIdParam", currentGymId)
				.getResultList();
	}
	
	//GET CURRENT GYMSPACEID
		private Long getLoadedSpaceId() throws Exception {
			Long currentGymId = getLoadedSpace().getId();
			return currentGymId;	
		}

		//GET CURRENT GYMSPACE
		private Space getLoadedSpace() throws Exception {

			// Get param from url
			Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String spaceIdParam = parameterMap.get("spaceId");

			if (spaceIdParam != null) {
				Space space = spaceDao.getSpaceById(Long.valueOf(spaceIdParam));
				return space;
			}
			else {
				LoginController loginController = new LoginController();
				Space space = spaceDao.getSpaceById(loginController.getLoggedAccount().getGymId());
				return space;
			}
		}


}

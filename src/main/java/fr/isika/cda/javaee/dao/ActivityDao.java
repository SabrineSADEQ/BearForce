package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityViewModel;

@Stateless
public class ActivityDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private EquipmentDao equipmentDao;
	
	public Activity createActivity (ActivityViewModel activityViewModel, List<Long> selectedEquipments) {
		Activity activityBean= new Activity();
		activityBean.setName(activityViewModel.getName());
		activityBean.setDescription(activityViewModel.getDescription());
		activityBean.setActiviteCategory(activityViewModel.getActivityCategory());	
		selectedEquipments
		.stream()
		.map(id -> equipmentDao.findEquipmentById(id))
		.forEach(equipment -> {
			equipment.setActivity(activityBean);
			activityBean.getEquipmentList().add(equipment);
			entityManager.merge(equipment);
		});
		entityManager.persist(activityBean);
		entityManager.flush();
		return activityBean;		
	}
	
	public void updateActivity(Activity updateActivity) {
		Activity existingActivity = entityManager.find(Activity.class, updateActivity.getId());
		existingActivity.setName(updateActivity.getName());
		existingActivity.setDescription(updateActivity.getDescription());
		existingActivity.setActiviteCategory(updateActivity.getActiviteCategory());
		entityManager.merge(existingActivity);
	}
	
	public void deleteActivity(Long activityToDeleteId) {
		Activity activitiToDelete = entityManager
				.createQuery("SELECT act FROM Activity act LEFT JOIN FETCH act.equipmentList WHERE act.id = :activityIdParam", Activity.class)
				.setParameter("activityIdParam", activityToDeleteId)
				.getSingleResult();
		entityManager.remove(activitiToDelete);
	}
	
	public List<Activity> getAllActivitiesWithEquipements() {
		return entityManager.createQuery("SELECT act FROM Activity act LEFT JOIN FETCH act.equipmentList", Activity.class).getResultList();
	}
	
	//SEAK IN DATABASE ALL ACTIVITIES
	public List<Activity> getAllActivities() {
		return entityManager
				.createQuery("SELECT a FROM Activity a LEFT JOIN FETCH a.equipmentList", Activity.class)
				.getResultList();
	}
	
	public List<Equipment> getAllEquipments() {
		return entityManager
				.createQuery("SELECT e FROM Equipment e", Equipment.class)
				.getResultList();
	}
	
	//SEAK IN DATABASE ACTIVITY BY ID
	public Activity findActivityById(long activityId) {
		System.out.println(activityId);
		return entityManager
				.createQuery("SELECT act FROM Activity act WHERE act.id = :activityIdParam", Activity.class)
				.setParameter("activityIdParam", activityId)
				.getSingleResult();
	}
	
	//SEAK IN DATABASE TRAINER BY ID
	public Profile findTrainerById(long trainerId) {
		return entityManager
				.createQuery("SELECT prof FROM Profile prof WHERE prof.id = :profileIdParam", Profile.class)
				.setParameter("profileIdParam", trainerId)
				.getSingleResult();
	}
	
	//SEAK IN DATABASE ALL TRAINERS
	public List<Profile> getAllTrainers(){
		return entityManager
				.createQuery("SELECT prof FROM Profile prof WHERE prof.account.role = 'COACH'", Profile.class)
				.getResultList();
	}

}
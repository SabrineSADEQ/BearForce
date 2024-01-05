package fr.isika.cda.javaee.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityViewModel;

@Stateless
public class ActivityDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Activity createActivity (ActivityViewModel activityViewModel) {
		Activity activityBean= new Activity();
		activityBean.setName(activityViewModel.getName());
		activityBean.setDescription(activityViewModel.getDescription());
		activityBean.setActiviteCategory(activityViewModel.getActivityCategory());
		activityBean.setEquipmentList(activityViewModel.getEquipmentList());
		entityManager.persist(activityBean);
		entityManager.flush();
		return activityBean;		
	}
	
	//SEAK IN DATABASE ALL ACTIVITIES
	public List<Activity> getAllActivities() {
		return entityManager
				.createQuery("SELECT a FROM Activity a", Activity.class)
				.getResultList();
	}
	
	//SEAK IN DATABASE ACTIVITY BY ID
	public Activity findActivityById(long activityId) {
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
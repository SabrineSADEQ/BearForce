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
	
	public List<Activity> getAllActivities() {
		return entityManager
				.createQuery("SELECT a FROM Activity a", Activity.class)
				.getResultList();
	}
	
	public Activity findActivityById(long activityId) {
		return entityManager
				.createQuery("SELECT act FROM Activity act WHERE act.id = :activityIdParam", Activity.class)
				.setParameter("activityIdParam", activityId)
				.getSingleResult();
	}
	
	//!!!!!!!!!!!!!!ATTENTION A METTRE DANS PROFILEDAO!!!!!!!!!!!!!!
	public Profile findTrainerById(long trainerId) {
		return entityManager
				.createQuery("SELECT prof FROM Profile prof WHERE prof.id = :profileIdParam", Profile.class)
				.setParameter("profileIdParam", trainerId)
				.getSingleResult();
	}
	
	//!!!!!!!!!!!!!!ATTENTION A METTRE DANS PROFILEDAO ET AJOUTER WHERE POUR SPECIFIER COACH!!!!!!!!!!!!!!
	public List<Profile> getAllTrainer(){
		return entityManager
				.createQuery("SELECT p FROM Profile p", Profile.class)
				.getResultList();
	}
}
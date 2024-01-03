package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;

@Stateless
public class ActivityDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void addActivity(String activity) {
		Activity a = new Activity();
		a.setName(activity);
		entityManager.persist(a);
	}

	public Activity getActivityById(Long id) {
		return entityManager.find(Activity.class, id);
	}

	public List<Activity> getAllActivities(){	
		return entityManager.createQuery("SELECT a FROM Activity a", Activity.class).getResultList();
	}

	public void deleteActivity(Long id) {
		Activity activity = entityManager.find(Activity.class, id);
		if (activity != null) {
			entityManager.remove(activity);
		}
	}
}
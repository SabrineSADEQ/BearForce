package fr.isika.cda.javaee.dao;

import java.util.ArrayList;
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
	
	public Activity createActivity (ActivityViewModel activityViewModel, List<Long> equipementsIdsForGivenactivity) {
		Activity activityBean= new Activity();
		activityBean.setName(activityViewModel.getName());
		activityBean.setDescription(activityViewModel.getDescription());
		activityBean.setActiviteCategory(activityViewModel.getActivityCategory());
		activityBean.setEquipmentList(activityViewModel.getEquipmentList());
		associateActivitiesWithEquipments(equipementsIdsForGivenactivity, activityBean);
		entityManager.persist(activityBean);
		entityManager.flush();
		return activityBean;		
	}
	
	public List<Equipment> getAllEquipmentsWithActivities() {
		return entityManager.createQuery("SELECT equi FROM Equipment equi LEFT JOIN FETCH equi.activityList", Equipment.class).getResultList();
	}
	
	private void associateActivitiesWithEquipments(List<Long> equipmentsIds, Activity activityBean) {
		List<Equipment> associated = new ArrayList<Equipment>();
		
		equipmentsIds
			.stream()
			.map(id -> equipmentDao.findEquipmentById(id))
			.forEach(equipment -> {
				equipment.getActivityList().add(activityBean);
				associated.add(equipment);
			});
		
		// associer l'quipement courant aux  activites (lien inverse) 
		activityBean.setEquipmentList(associated);
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
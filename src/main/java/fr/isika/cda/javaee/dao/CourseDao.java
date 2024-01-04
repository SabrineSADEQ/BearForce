package fr.isika.cda.javaee.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.presentation.viewmodel.CourseViewModel;

@Stateless
public class CourseDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private ActivityDao activityDao;
	
	public Course createCourse (CourseViewModel courseViewModel) {
		Course courseBean = new Course();
		Activity act = findActivityById(courseViewModel.getActivityId());
		courseBean.setActivity(act);
		courseBean.setStartDate(courseViewModel.getStartDate());
		courseBean.setEndDate(courseViewModel.getEndDate());
		courseBean.setNbPlaces(courseViewModel.getNbPlaces());
		//!!!!!!!!!!!!!!ATTENTION RELIER AU PROFIL UNE FOIS LE MERGE FAIT!!!!!!!!!!!!!!
		//Profile trainer = findTrainerById(courseViewModel.getTrainerId());
		//courseBean.setTrainer(trainer);
		entityManager.persist(courseBean);
		entityManager.flush();
		return courseBean;	
	}

	private Activity findActivityById(long activityId) {
		return activityDao.findActivityById(activityId);
	}
	
	//!!!!!!!!!!!!!!ATTENTION CHANGER POUR PROFILEDAO UNE FOIS LE DAO EXPORTE!!!!!!!!!!!!!!
	private Profile findTrainerById(long trainerId) {
		return activityDao.findTrainerById(trainerId);
	}
	
	
}

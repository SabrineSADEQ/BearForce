
package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.presentation.viewmodel.CourseViewModel;

@Stateless
public class CourseDAO {

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
	
	public void saveCourse(Course course) {
		entityManager.persist(course);
	}
	
	public Course getCourseByIdJoinActivity(long courseId) {
		return entityManager
				.createQuery("SELECT c FROM Course c LEFT JOIN FETCH c.activity WHERE c.id = :courseIdParam",
						Course.class)
				.setParameter("courseIdParam", courseId).getSingleResult();
	}

	public List<Course> getAllCourses() {
		return entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
	}

	public List<Course> getAllCoursesWithActivities() {
		return entityManager.createQuery("SELECT c FROM Course c LEFT JOIN FETCH c.activity", Course.class)
				.getResultList();
	}

}
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
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.presentation.viewmodel.CourseViewModel;
import fr.isika.cda.javaee.utils.SessionUtils;

@Stateless
public class CourseDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private ActivityDao activityDao;

	@Inject
	private SpaceDao spaceDao;

	public Course createCourse (CourseViewModel courseViewModel) {
		Course courseBean = new Course();
		Activity act = activityDao.findActivityById(courseViewModel.getActivityId());
		courseBean.setActivity(act);
		courseBean.setStartDate(courseViewModel.getStartDate());
		courseBean.setEndDate(courseViewModel.getEndDate());
		courseBean.setNbPlaces(courseViewModel.getNbPlaces());
		Profile trainer = activityDao.findTrainerById(courseViewModel.getTrainerId());
		courseBean.setTrainer(trainer);
		entityManager.persist(courseBean);
		entityManager.flush();
		return courseBean;	
	}

	public void mergeCourse(Course courseToMerge) {
		entityManager.merge(courseToMerge);
	}

	public void updateCourse(Course updateCourse, Long selectedActivity, Long selectedProfile) {
		Course existingCourse = entityManager.find(Course.class, updateCourse.getId());
		Activity activity = activityDao.findActivityById(selectedActivity);
		existingCourse.setActivity(activity);
		existingCourse.setStartDate(updateCourse.getStartDate());
		existingCourse.setEndDate(updateCourse.getEndDate());
		Profile trainer = activityDao.findTrainerById(selectedProfile);
		existingCourse.setNbPlaces(updateCourse.getNbPlaces());
		existingCourse.setTrainer(trainer);
		entityManager.merge(existingCourse);
	}

	public void deleteCourse(Long courseToDeleteId) {
		Course courseToDelete = entityManager
				.createQuery("SELECT c FROM Course c LEFT JOIN FETCH c.activity WHERE c.id = :courseIdParam", Course.class)
				.setParameter("courseIdParam", courseToDeleteId)
				.getSingleResult();
		entityManager.remove(courseToDelete);
	}

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
		Account logged = SessionUtils.getAccount();
//		LoginController controller = new LoginController();
//		Account logged = controller.getLoggedAccount();
		Long loggedAccountGymId = logged.getGymId();	
		return entityManager
				.createQuery("SELECT c FROM Course c LEFT JOIN FETCH c.activity WHERE c.activity.attachedGymId = :gymIdParam"
						, Course.class)
				.setParameter("gymIdParam", loggedAccountGymId)
				.getResultList();
	}

	public List<Course> getAllCoursesOfConnectedCoach() {
		Account logged = SessionUtils.getAccount();
//		LoginController controller = new LoginController();
//		Account logged = controller.getLoggedAccount();
		Long loggedAccountId = logged.getId();	
		return entityManager
				.createQuery("SELECT c FROM Course c WHERE c.trainer.account.id = :accountIdParam"
						, Course.class)
				.setParameter("accountIdParam", loggedAccountId)
				.getResultList();
	}
	
	public List<Course> getAllCoursesOfGymId() throws Exception {
		Long currentGymId = getLoadedSpaceId();	
		return entityManager
				.createQuery("SELECT c FROM Course c WHERE c.activity.attachedGymId = :gymIdParam"
						, Course.class)
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

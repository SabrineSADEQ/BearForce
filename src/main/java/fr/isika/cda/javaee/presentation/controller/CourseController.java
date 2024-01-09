package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import fr.isika.cda.javaee.dao.CourseDAO;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.presentation.viewmodel.CourseViewModel;

@Named
@ViewScoped
public class CourseController implements Serializable{

	private static final long serialVersionUID = 1L;

	private CourseViewModel courseViewModel = new CourseViewModel();
	private Long selectedActivity;
	private Long selectedProfile;
	private Course selectedCourse;
	

	@Inject
	private CourseDAO courseDao;

	@PostConstruct
	public void init() {
		System.out.println("CourseController bean initialized!");
	}

	public void addCourse() {
		courseDao.createCourse(courseViewModel);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Un nouveau cours à été ajouté au planning pour le " + courseViewModel.getStartDate() + "."));
		courseViewModel =  new CourseViewModel();
	}
	
	//MODIFIE COURSE IN DATABASE
		public void modifieSelectedCourse() {
			courseDao.updateCourse(selectedCourse, selectedActivity, selectedProfile);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cours mise à jour"));
			PrimeFaces.current().executeScript("PF('manageCourseDialog').hide()");
		}
		
		//DELETE COURSE FROM DATABASE
		public void deleteSelectedCourse() {
			courseDao.deleteCourse(selectedCourse.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cours annulé"));
		}


	public List<Course> displayCoursesList() {
		return courseDao.getAllCoursesWithActivities();
	}

	//***************GETTERS & SETTERS***************
	public CourseViewModel getCourseViewModel() {
		return courseViewModel;
	}

	public void setCourseViewModel(CourseViewModel courseViewModel) {
		this.courseViewModel = courseViewModel;
	}

	public Course getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(Course selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

	public Long getSelectedActivity() {
		return selectedActivity;
	}

	public void setSelectedActivity(Long selectedActivity) {
		this.selectedActivity = selectedActivity;
	}

	public Long getSelectedProfile() {
		return selectedProfile;
	}

	public void setSelectedProfile(Long selectedProfile) {
		this.selectedProfile = selectedProfile;
	}
		
}

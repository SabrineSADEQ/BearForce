package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.CourseDAO;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.presentation.viewmodel.CourseViewModel;

@Named
@ViewScoped
public class CourseController implements Serializable{

	private static final long serialVersionUID = 1L;

	private CourseViewModel courseViewModel = new CourseViewModel();

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

	public List<Course> getAllCourses() {
		return courseDao.getAllCoursesWithActivities();
	}

	//***************GETTERS & SETTERS***************
	public CourseViewModel getCourseViewModel() {
		return courseViewModel;
	}

	public void setCourseViewModel(CourseViewModel courseViewModel) {
		this.courseViewModel = courseViewModel;
	}
}

package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import fr.isika.cda.javaee.dao.CourseDao;
import fr.isika.cda.javaee.presentation.viewmodel.CourseViewModel;

@Named
@ViewScoped
public class CourseController implements Serializable{

	private static final long serialVersionUID = 1L;

	private CourseViewModel courseViewModel = new CourseViewModel();

	@Inject
	private CourseDao courseDao;

	@PostConstruct
	public void init() {
		System.out.println("CourseController bean initialized!");
	}

	public void addCourse() {
		courseDao.createCourse(courseViewModel);
		courseViewModel =  new CourseViewModel();
	}

	//***************GETTERS & SETTERS***************
	
	public CourseViewModel getCourseViewModel() {
		return courseViewModel;
	}

	public void setCourseViewModel(CourseViewModel courseViewModel) {
		this.courseViewModel = courseViewModel;
	}
}

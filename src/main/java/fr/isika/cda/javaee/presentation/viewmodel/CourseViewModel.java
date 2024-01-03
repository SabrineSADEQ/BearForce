package fr.isika.cda.javaee.presentation.viewmodel;


	
	import fr.isika.cda.javaee.entity.gymspace.business.Course;

	import java.util.List;

	public class CourseViewModel{

	    private List<Course> courseList;
	    private Course selectedCourse;

	    public CourseViewModel(List<Course> courseList, Course selectedCourse) {
	        this.courseList = courseList;
	        this.selectedCourse = selectedCourse;
	    }

	    public List<Course> getCourseList() {
	        return courseList;
	    }

	    public void setCourseList(List<Course> courseList) {
	        this.courseList = courseList;
	    }

	    public Course getSelectedCourse() {
	        return selectedCourse;
	    }

	    public void setSelectedCourse(Course selectedCourse) {
	        this.selectedCourse = selectedCourse;
	    }
	}


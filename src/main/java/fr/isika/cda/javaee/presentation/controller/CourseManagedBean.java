
package fr.isika.cda.javaee.presentation.controller;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.CourseDAO;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Course;

@Named
@SessionScoped
public class CourseManagedBean implements Serializable {

    /**
     * @author Bitjoka Vincent
     */
	private static final long serialVersionUID = 6550982393993817037L;

	@Inject
    private CourseDAO courseDAO;

    private List<Course> courseList;
    private Course selectedCourse;
    

    @PostConstruct
    public void init() {
        refreshCourseList();
        this.selectedCourse = null;
    }

    
    
    public void saveCourse(Course course) {
        courseDAO.saveCourse(course);
        refreshCourseList();
    }

    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
        refreshCourseList();
    }

    public void deleteCourse(long courseId) {
        courseDAO.deleteCourse(courseId);
        refreshCourseList();
    }
    
    public String viewDetails(Long courseId) {
    	this.selectedCourse = courseDAO.getCourseByIdJoinActivity(courseId);
    	return "courseDetails";
    }
    
    public void createTestCourses() {
    	Activity a = new Activity();
		a.setName("Test activity");
		a.setDescription("Bla");
		
		Course c = new Course();
		c.setStartDate(LocalDateTime.now());
		c.setEndDate(LocalDateTime.now());
		c.setNbPlaces(5);
		
		c.setActivity(a);
		
		courseDAO.saveCourseWithActivity(c, a);
		refreshCourseList();
    }
    
    private void refreshCourseList() {
        courseList = courseDAO.getAllCoursesWithActivities();
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

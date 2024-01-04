
package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Course;

@Stateless
public class CourseDAO {

    @PersistenceContext
    private EntityManager entityManager;
   
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }
    public void saveCourseWithActivity(Course course, Activity activity) {
    	entityManager.persist(activity);
    	entityManager.persist(course);
    }
    
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }
    
    public void deleteCourse(long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        if (course != null) {
            entityManager.remove(course);
        }
    }

    public Course getCourseById(long courseId) {
        return entityManager.find(Course.class, courseId);
    }
    public Course getCourseByIdJoinActivity(long courseId) {
    	return entityManager.createQuery("SELECT c FROM Course c LEFT JOIN FETCH c.activity WHERE c.id = :courseIdParam", Course.class)
    			.setParameter("courseIdParam", courseId)
    			.getSingleResult();
    }

    public List<Course> getAllCourses() {
        return entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

    public List<Course> getAllCoursesWithActivities() {
        return entityManager.createQuery("SELECT c FROM Course c LEFT JOIN FETCH c.activity", Course.class).getResultList();
    }
    
}


package fr.isika.cda.javaee.dao;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Course;

@Startup
@Singleton
public class DatabaseInit {

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	@PostConstruct
//	private void init() {
//		
//		Activity a = new Activity();
//		a.setName("Test activity");
//		a.setDescription("Bla");
//		
//		Course c = new Course();
//		c.setStartDate(LocalDateTime.now());
//		c.setEndDate(LocalDateTime.now());
//		c.setNbPlaces(5);
//		
//		c.setActivity(a);
//		
//		entityManager.persist(a);
//		entityManager.persist(c);
//	}
}

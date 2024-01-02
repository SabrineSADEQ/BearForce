package fr.isika.cda.javaee.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
//@Startup
public class ApplicationDao {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
	}
	
}

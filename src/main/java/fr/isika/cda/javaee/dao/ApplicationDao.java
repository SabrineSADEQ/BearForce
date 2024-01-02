package fr.isika.cda.javaee.dao;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.isika.cda.javaee.entity.ApplicationEntity;

@Stateless
@Startup
public class ApplicationDao {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		ApplicationEntity app = new ApplicationEntity();
		app.setName("Isika CDA Final Project (JavaEE)");
		app.setDescription("Webapp with JavaEE technology (JSF, JPA, EJBs) / 3-tiers architecture / MVC pattern.");
		
		entityManager.persist(app);
	}
	
	public List<ApplicationEntity> all() {
		return entityManager
				.createQuery("SELECT app FROM ApplicationEntity app", ApplicationEntity.class)
				.getResultList();
	}
	
	public ApplicationEntity last() {
		return all().stream()
				.sorted((o1, o2) -> o1.getCreationDate().compareTo(o2.getCreationDate()))
				.findFirst()
				.get();
	}
}

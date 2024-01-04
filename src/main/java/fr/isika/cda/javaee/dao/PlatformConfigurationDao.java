package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.platform.PlatformConfiguration;

/**
 * 
 * @author SADEQ Sabrine
 *
 */

@Stateless
@LocalBean
public class PlatformConfigurationDao implements PlatformConfigurationInterface {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PlatformConfiguration getPlatformConfiguration(Long id) {
		return entityManager.find(PlatformConfiguration.class, id);
	}

	@Override
	public List<PlatformConfiguration> getPlatformConfigurations() {
		return entityManager.createQuery("SELECT pc FROM PlatformConfiguration pc", PlatformConfiguration.class)
				.getResultList();
	}

	@Override
	public void createPlatformConfiguration(PlatformConfiguration platformConfiguration) {
		entityManager.persist(platformConfiguration);
	}

	@Override
	public void updatePlatformConfiguration(PlatformConfiguration platformConfiguration) {
		entityManager.merge(platformConfiguration);
	}

	@Override

	public void deletePlatformConfiguration(int id) {
		PlatformConfiguration platformConfiguration = entityManager.find(PlatformConfiguration.class, id);
		if (platformConfiguration != null) {
			entityManager.remove(platformConfiguration);
		}
	}

}
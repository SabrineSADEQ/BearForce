package fr.isika.cda.javaee.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fr.isika.cda.javaee.entity.platform.PlatformConfiguration;

/**
 * 
 * @author SADEQ Sabrine
 *
 */

@Stateless
public class PlatformConfigurationDao implements PlatformConfigurationInterface{


	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PlatformConfiguration GetPlatformConfiguration(Long id) {
		
		 return entityManager.find(PlatformConfiguration.class, id);
	}

	@Override
	public List<PlatformConfiguration> GetPlatformConfigurations() {
		
		return entityManager.createQuery("SELECT pc FROM PlatformConfiguration pc", PlatformConfiguration.class)
                .getResultList();

	}
		
	@Transactional
	@Override
	public Long CreatePlatformConfiguration(PlatformConfiguration platformConfiguration) {
		entityManager.persist(platformConfiguration);
        return platformConfiguration.getId();
		
	}
	@Transactional
	@Override
	public void UpdatePlatformConfiguration(PlatformConfiguration platformConfiguration) {
		entityManager.merge(platformConfiguration);
		
	}
	
	@Transactional
	@Override
	
	public void DeletePlatformConfiguration(int id) {
		
		PlatformConfiguration platformConfiguration = entityManager.find(PlatformConfiguration.class, id);
        if (platformConfiguration != null) {
            entityManager.remove(platformConfiguration);
            
	}
	
	
	}
}

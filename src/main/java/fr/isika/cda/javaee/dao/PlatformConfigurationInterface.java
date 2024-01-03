package fr.isika.cda.javaee.dao;

import java.util.List;

import fr.isika.cda.javaee.entity.platform.PlatformConfiguration;

/**
 * 
 * @author SADEQ Sabrine
 *
 */
public interface PlatformConfigurationInterface {
	
	
	PlatformConfiguration GetPlatformConfiguration(Long id);
	
	List<PlatformConfiguration> GetPlatformConfigurations();
	
	Long CreatePlatformConfiguration(PlatformConfiguration platformConfiguration);
	
	void UpdatePlatformConfiguration(PlatformConfiguration platformConfiguration);
	
	
	void DeletePlatformConfiguration(int id);
	
	

}

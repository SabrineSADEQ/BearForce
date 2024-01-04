package fr.isika.cda.javaee.dao;

import java.util.List;

import fr.isika.cda.javaee.entity.platform.PlatformConfiguration;

/**
 * 
 * @author SADEQ Sabrine
 *
 */
public interface PlatformConfigurationInterface {
	
	
	PlatformConfiguration getPlatformConfiguration(Long id);
	
	//PlatformConfiguration getPlatformConfiguration(String strId);
	
	List<PlatformConfiguration> getPlatformConfigurations();
	
	void createPlatformConfiguration(PlatformConfiguration platformConfiguration);
	
	void updatePlatformConfiguration(PlatformConfiguration platformConfiguration);
	
	
	void deletePlatformConfiguration(int id);
	
	

}
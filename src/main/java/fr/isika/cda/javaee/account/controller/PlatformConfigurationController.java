package fr.isika.cda.javaee.account.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.PlatformConfigurationDao;
import fr.isika.cda.javaee.entity.platform.PlatformConfiguration;


/**
 * 
 * @author Sabrine SADEQ
 *
 */

@Named
@ViewScoped
public class PlatformConfigurationController implements Serializable {

	private static final long serialVersionUID = 1L;

	private PlatformConfiguration platformConfiguration = new PlatformConfiguration();
	
	private List<PlatformConfiguration> plaltformConfigurations;
	@Inject
	private PlatformConfigurationDao platformConfigurationDao;

	@PostConstruct
	public void init() {
		System.out.println("PlatformConfigurationController bean initialized!");
		
	}

	public void createConfiguration() {
		try {
			platformConfigurationDao.createPlatformConfiguration(platformConfiguration);
			platformConfiguration = new PlatformConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<PlatformConfiguration> getAllConfigurations() {
		return platformConfigurationDao.getPlatformConfigurations();
	}
	
	
	public PlatformConfiguration getPlatformConfiguration() {
		return platformConfiguration;
	}
	public void setPlatformConfiguration(PlatformConfiguration platformConfiguration) {
		this.platformConfiguration = platformConfiguration;
	}
	
	public List<PlatformConfiguration> getPlatformConfigurations() {
		return plaltformConfigurations;
	}

	public void setPlatformConfigurations(List<PlatformConfiguration> platformConfigurations) {
		this.plaltformConfigurations= platformConfigurations;
	}
	
}

package fr.isika.cda.javaee.account.controller;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.accounts.ProfilesManagementDao;
import fr.isika.cda.javaee.entity.accounts.Profile;




@Named
@ViewScoped
public class ProfilesManagementController implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ProfilesManagementDao profilesManagementDao;
	
	private List<Profile> profiles;
	
	@PostConstruct
	public void init() {
		profiles = profilesManagementDao.getAllProfilesWithRoleCoach();
	}
	
	public List<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
}



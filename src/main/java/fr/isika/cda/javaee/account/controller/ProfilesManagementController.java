package fr.isika.cda.javaee.account.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.accounts.ProfilesManagementDao;
import fr.isika.cda.javaee.entity.accounts.Profile;

@Named
@javax.enterprise.context.SessionScoped
public class ProfilesManagementController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfilesManagementDao profilesManagementDao;

//	private List<Profile> profiles;

	private Profile selectedProfile;

	@PostConstruct
	public void init() {
//		profiles = profilesManagementDao.getAllProfilesWithRoleCoach();
	}

	public void setSelectedProfile(Profile profile) {
		this.selectedProfile = profile;
	}

	public List<Profile> getProfiles() {
		return profilesManagementDao.getAllProfilesWithRoleCoach();
	}

//	public void setProfiles(List<Profile> profiles) {
//		this.profiles = profiles;
//	}

	public Profile getSelectedProfile() {
		return selectedProfile;
	}
}

package fr.isika.cda.javaee.account.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.accounts.ProfilesManagementDao;
import fr.isika.cda.javaee.entity.accounts.Profile;

@Named
@ViewScoped
public class ProfilesManagementController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfilesManagementDao profilesManagementDao;

	private Profile selectedProfile;

	public Profile getSelectedProfile() {
		return selectedProfile;
	}
	
	public void setSelectedProfile(Profile profile) {
		this.selectedProfile = profile;
	}

	public List<Profile> getProfiles() {
		return profilesManagementDao.getAllProfilesWithRoleCoach();
	}
	
}

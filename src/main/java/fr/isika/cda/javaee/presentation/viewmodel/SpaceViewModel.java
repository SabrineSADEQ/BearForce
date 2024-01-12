package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.accounts.Address;
import fr.isika.cda.javaee.entity.accounts.Contact;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.IdGym;

public class SpaceViewModel {

	private Long id;

	// VisualIdentity attributes
	private String firstColor;
	private String secondColor;
	private String thirdColor;
	private String gymLogoPath;
	private String gymBannerPath;

	// SpaceTextContent attributes
	private String spaceName;
	private String motto;
	private String description;

	// GymCaracteristics attributes
	private boolean freeAccess;
	private boolean lockerRoom;
	private String photoList;
	
	//IdGym
	private IdGym idGym = new IdGym();

	// AdminInfoGym attributes
	private String siret;
	private Address location = new Address();
	private Contact contactInfo = new Contact();

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public Contact getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(Contact contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getGymLogoPath() {
		return gymLogoPath;
	}

	public void setGymLogoPath(String gymLogoPath) {
		this.gymLogoPath = gymLogoPath;
	}

	public String getGymBannerPath() {
		return gymBannerPath;
	}

	public void setGymBannerPath(String gymBannerPath) {
		this.gymBannerPath = gymBannerPath;
	}

	public String getFirstColor() {
		return firstColor;
	}

	public void setFirstColor(String firstColor) {
		this.firstColor = firstColor;
	}

	public String getSecondColor() {
		return secondColor;
	}

	public void setSecondColor(String secondColor) {
		this.secondColor = secondColor;
	}

	public String getThirdColor() {
		return thirdColor;
	}

	public void setThirdColor(String thirdColor) {
		this.thirdColor = thirdColor;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String textArea) {
		this.description = textArea;
	}

	public Long getId() {
		return id;
	}

	public boolean getFreeAccess() {
		return freeAccess;
	}

	public void setFreeAccess(boolean freeAccess) {
		this.freeAccess = freeAccess;
	}

	public boolean getLockerRoom() {
		return lockerRoom;
	}

	public void setLockerRoom(boolean lockerRoom) {
		this.lockerRoom = lockerRoom;
	}

	public String getPhotoList() {
		return photoList;
	}

	public void setPhotoList(String photoList) {
		this.photoList = photoList;
	}

	public IdGym getIdGym() {
		return idGym;
	}

	public void setIdGym(IdGym idGym) {
		this.idGym = idGym;
	}
	
	

}

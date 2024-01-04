package fr.isika.cda.javaee.presentation.viewmodel;


public class SpaceViewModel {
	
	private Long id;
	
	//VisualIdentity attributes
	private String firstColor;
	private String secondColor;
	private String thirdColor;
	private byte[] gymLogo ;
	private byte[] gymBanner ;
	
	//SpaceTextContent attributes
	private String spaceName;
	private String motto;
	private String description;

	//GymCaracteristics attributes
	private boolean freeAccess;
	private boolean lockerRoom;
	private String photoList;
	
	
	public byte[] getGymLogo() {
		return gymLogo;
	}
	public void setGymLogo(byte[] gymLogo) {
		this.gymLogo = gymLogo;
	}
	public byte[] getGymBanner() {
		return gymBanner;
	}
	public void setGymBanner(byte[] gymBanner) {
		this.gymBanner = gymBanner;
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
	
	
	
	
}

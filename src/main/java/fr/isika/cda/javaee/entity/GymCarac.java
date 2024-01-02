package fr.isika.cda.javaee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GymCarac {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean libreAccess;
	private boolean lockerRoom;
	private String photoList;
	
	@OneToOne
	private IdGym idGym;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isLibreAccess() {
		return libreAccess;
	}
	public void setLibreAccess(boolean libreAccess) {
		this.libreAccess = libreAccess;
	}
	public boolean isLockerRoom() {
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


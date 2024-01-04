package fr.isika.cda.javaee.entity.gymspace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/**
 * 
 * @author Bitjoka Vincent
 */
@Entity
public class GymCaracteristics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean freeAccess;
	private boolean lockerRoom;
	private String photoList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isLibreAccess() {
		return freeAccess;
	}
	public void setLibreAccess(boolean libreAccess) {
		this.freeAccess = libreAccess;
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


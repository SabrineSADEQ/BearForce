package fr.isika.cda.javaee.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="profile")
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lasName;
	private LocalDate birthDate;
	private String pictureUrl;
	@OneToOne
	private Contact contact;
	@OneToOne
	private Address address;
	@OneToOne
	private ProfesionalDetails profesionalDetails;
	
	//***************GETTERS & SETTERS***************
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLasName() {
		return lasName;
	}
	public void setLasName(String lasName) {
		this.lasName = lasName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ProfesionalDetails getProfesionalDetails() {
		return profesionalDetails;
	}
	public void setProfesionalDetails(ProfesionalDetails profesionalDetails) {
		this.profesionalDetails = profesionalDetails;
	}
	
}

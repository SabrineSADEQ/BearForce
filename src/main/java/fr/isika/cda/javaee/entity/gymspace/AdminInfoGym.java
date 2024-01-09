package fr.isika.cda.javaee.entity.gymspace;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.isika.cda.javaee.entity.accounts.Address;
import fr.isika.cda.javaee.entity.accounts.Contact;
import fr.isika.cda.javaee.entity.accounts.Profile;

@Entity
@Table(name = "Admin_Info_Gym")
public class AdminInfoGym {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(name = "siret")
	private String siret;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address location;

	@OneToOne(cascade = CascadeType.ALL)
	private Contact contactInfo;


	public AdminInfoGym() {
	}

	public AdminInfoGym(String siret, Address location, Contact contact) {
		this.siret = siret;
		this.location = location;
		this.contactInfo = contact;
	}

	public long getId() {
		return id;
	}

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

}

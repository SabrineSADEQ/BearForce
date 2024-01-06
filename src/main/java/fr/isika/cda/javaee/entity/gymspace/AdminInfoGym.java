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
	
	@OneToOne
	@Column(name = "owner")
	private Profile owner;
	
	@Column(name = "siret")
	private String siret;
	
	@OneToOne
	@Column(name = "location")
	private Address location;

	@OneToOne
	private IdGym idGym;
	
	@OneToOne
	private Contact contactInfo;

	public IdGym getIdGym() {
		return idGym;
	}

	public void setIdGym(IdGym idGym) {
		this.idGym = idGym;
	}

	public AdminInfoGym() {
	}

	public AdminInfoGym(Profile owner, String siret, Address location) {
		this.owner = owner;
		this.siret = siret;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
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

}

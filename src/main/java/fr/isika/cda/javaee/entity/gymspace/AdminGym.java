package fr.isika.cda.javaee.entity.gymspace;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Admin_Gym")
public class AdminGym {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "owner")
	private String owner;
	@Column(name = "siret")
	private String siret;
	@Column(name = "location")
	private String location;

	@OneToMany(cascade = CascadeType.ALL)
	private List<IdGym> IdsGym;

	public AdminGym() {
	}

	public AdminGym(String owner, String siret, String location) {
		this.owner = owner;
		this.siret = siret;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}

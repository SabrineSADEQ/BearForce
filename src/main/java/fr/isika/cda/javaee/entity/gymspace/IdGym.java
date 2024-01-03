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

@Entity
@Table (name="IdGym")

public class IdGym {
	
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private long id;
	
	@Column (name="gym_Name") 
	private String gymName;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Membership> memberships;
	
	@OneToOne
	private AdminInfoGym adminInfoGym;
	
	@OneToOne
	private GymCaracteristics gymCaracteristics;
	
	
	
	public GymCaracteristics getGymCaracteristics() {
		return gymCaracteristics;
	}

	public void setGymCaracteristics(GymCaracteristics gymCaracteristics) {
		this.gymCaracteristics = gymCaracteristics;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	public AdminInfoGym getAdminInfoGym() {
		return adminInfoGym;
	}

	public void setAdminInfoGym(AdminInfoGym adminInfoGym) {
		this.adminInfoGym = adminInfoGym;
	}

	public IdGym() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IdGym(String gymName) {
		super();
		this.gymName = gymName;
	}

	public long getId() {
		return id;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	
	
	
	
	

}

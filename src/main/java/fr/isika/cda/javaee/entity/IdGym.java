package fr.isika.cda.javaee.entity;

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
@Table (name="IdGym")

public class IdGym {
	
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private long id;
	
	@Column (name="gym_Name") 
	private String gymName;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Membership> memberships;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<AdminGym> adminsGym;
	
	
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

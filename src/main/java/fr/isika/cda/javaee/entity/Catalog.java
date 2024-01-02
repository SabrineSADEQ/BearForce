package fr.isika.cda.javaee.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/** 
 * Represents a catalog (a group of other objects such as equipments or activities)
 * @author Floriane D. 
 */
@Entity
public class Catalog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	
	private String name ;
	
	private enum CatalogCategory {
		ACTIVITES, EQUIPEMENTS
	}
	
	@OneToMany
	@Column(name="activity_list")
	private List<Activity> activityList = new ArrayList<>() ;
	
	@OneToMany
	@Column(name="equipment_list")
	private List<Equipment> equipmentList = new ArrayList<>() ;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Activity> getActivityList() {
		return activityList;
	}
	
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

}

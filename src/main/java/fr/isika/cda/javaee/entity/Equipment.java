package fr.isika.cda.javaee.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/** 
 * Represents an equipment
 * @author Floriane D. 
 */
@Entity
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	
	private String type ;
	private int quantity ;
	
	@Column(name="equipment_name")
	private String equipmentName;
	private String details ;
	
	@ManyToMany(mappedBy = "equipmentList")
	@Column(name="activity_list")
	private List<Activity> activityList = new ArrayList<>() ;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public long getId() {
		return id;
	}
	
	
	
}

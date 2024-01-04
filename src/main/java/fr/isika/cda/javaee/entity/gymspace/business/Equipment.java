package fr.isika.cda.javaee.entity.gymspace.business;

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
	
	// Eviter le mot clé "condition" apparemment dans mysql
	@Column(name = "usageCondition")
	private String condition ;
	
	private int quantity ;	
	@Column(name="equipment_name")
	private String equipmentName;
	private String details ;
	
	@ManyToMany(mappedBy = "equipmentList")
	//@Column(name="activity_list")
	private List<Activity> activityList = new ArrayList<>() ;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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

package fr.isika.cda.javaee.entity.gymspace.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	@ManyToOne
	private Activity activity;

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

	public long getId() {
		return id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
}

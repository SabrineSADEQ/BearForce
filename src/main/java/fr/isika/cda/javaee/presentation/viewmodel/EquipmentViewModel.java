package fr.isika.cda.javaee.presentation.viewmodel;

import java.util.ArrayList;
import java.util.List;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;

public class EquipmentViewModel {
	private long id ;
	private String condition ;
	private int quantity ;
	private String equipmentName;
	private String details ;
	private List<Activity> activityList = new ArrayList<>() ;
	
	//***************GETTERS & SETTERS***************
	public String getCondition() {
		return condition;
	}
	public void setCondition(String type) {
		this.condition = type;
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

package fr.isika.cda.javaee.presentation.viewmodel;

import fr.isika.cda.javaee.entity.gymspace.business.Activity;

public class EquipmentViewModel {
	private long id ;
	private long activityId;
	private String condition ;
	private int quantity ;
	private String equipmentName;
	private String details ;
	private Activity activity;
	
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
	public long getId() {
		return id;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public long getActivityId() {
		return activityId;
	}
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
			
}

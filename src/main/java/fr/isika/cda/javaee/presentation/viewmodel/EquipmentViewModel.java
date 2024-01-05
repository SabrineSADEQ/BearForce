package fr.isika.cda.javaee.presentation.viewmodel;

public class EquipmentViewModel {
	private long id ;
	private String condition ;
	private int quantity ;
	private String equipmentName;
	private String details ;
	
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
		
}

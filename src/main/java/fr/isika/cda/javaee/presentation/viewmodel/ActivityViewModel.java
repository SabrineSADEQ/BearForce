package fr.isika.cda.javaee.presentation.viewmodel;

import java.util.ArrayList;
import java.util.List;
import fr.isika.cda.javaee.entity.gymspace.business.ActivityCategory;
import fr.isika.cda.javaee.entity.gymspace.business.Course;
import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.entity.gymspace.business.Training;

public class ActivityViewModel {
	
	private Long id;
	private String name;
	private String description;
	private ActivityCategory activityCategory;
	private List<Course> courseList = new ArrayList<>() ;
	private List<Equipment> equipmentList = new ArrayList<>() ;
	
	//***************GETTERS & SETTERS***************
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}
	public Long getId() {
		return id;
	}
	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}
	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}	
	
}

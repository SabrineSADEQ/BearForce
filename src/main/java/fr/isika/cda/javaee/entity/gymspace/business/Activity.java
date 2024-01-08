package fr.isika.cda.javaee.entity.gymspace.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/** 
 * Represents an activity (a lesson type without a date and time)
 * @author Floriane D. 
 */

@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;	
	private String name ;
	private String description;	
	@Enumerated(EnumType.STRING)
	private ActivityCategory activityCategory;	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activity")
	@Column(name="course_list")
	private List<Course> courseList = new ArrayList<>() ; 	
	@ManyToMany
	@JoinTable(name = "activity_equipment",
	joinColumns = @JoinColumn(name = "activity_id"),
	inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private List<Equipment> equipmentList = new ArrayList<>() ;

	//***************GETTERS & SETTERS***************
	public ActivityCategory getCategory() {
		return activityCategory;
	}

	public void setCategory(ActivityCategory category) {
		this.activityCategory = category;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

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

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public long getId() {
		return id;
	}

	public ActivityCategory getActiviteCategory() {
		return activityCategory;
	}

	public void setActiviteCategory(ActivityCategory activiteCategory) {
		this.activityCategory = activiteCategory;
	} 

}

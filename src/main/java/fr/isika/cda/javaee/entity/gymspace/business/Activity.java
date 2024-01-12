package fr.isika.cda.javaee.entity.gymspace.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Long attachedGymId;
	@Enumerated(EnumType.STRING)
	private ActivityCategory activityCategory;	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
	private List<Course> courseList = new ArrayList<>() ;	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
	private List<Equipment> equipmentList = new ArrayList<>() ;
	private String activityPicturePath;

	//***************GETTERS & SETTERS***************

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

	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}
	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}

	public Long getAttachedGymId() {
		return attachedGymId;
	}

	public void setAttachedGymId(Long attachedGymId) {
		this.attachedGymId = attachedGymId;
	}

	public String getActivityPicturePath() {
		return activityPicturePath;
	}

	public void setActivityPicturePath(String activityPicturePath) {
		this.activityPicturePath = activityPicturePath;
	}
	
}

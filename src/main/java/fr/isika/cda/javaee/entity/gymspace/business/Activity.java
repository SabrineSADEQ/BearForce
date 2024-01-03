package fr.isika.cda.javaee.entity.gymspace.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@OneToMany(cascade = CascadeType.ALL)
	@Column(name="training_list")
	private List<Training> trainingList = new ArrayList<>() ; 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activity")
	@Column(name="course_list")
	private List<Course> courseList = new ArrayList<>() ; 
	
	
	@ManyToMany
	@JoinTable(name = "activity_equipment",
				joinColumns = @JoinColumn(name = "activity_id"),
				inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private List<Equipment> equipmentList = new ArrayList<>() ;

	
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

	public List<Training> getTrainingList() {
		return trainingList;
	}

	public void setTrainingList(List<Training> trainingList) {
		this.trainingList = trainingList;
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
	
	
}

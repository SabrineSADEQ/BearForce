package fr.isika.cda.javaee.entity.gymspace.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** 
 * Represents a training program, which is a set of activities with a description
 * @author Floriane D. 
 */

@Entity
public class Training {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	
	@Column(name="program_name")
	private String programName;
	
	@Column(name="program_description")
	private String programDescription;
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgramDescription() {
		return programDescription;
	}
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	public long getId() {
		return id;
	}
	
	
}

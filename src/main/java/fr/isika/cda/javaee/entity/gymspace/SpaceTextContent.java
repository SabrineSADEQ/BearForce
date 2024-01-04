package fr.isika.cda.javaee.entity.gymspace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
/**
 * 
 * @author Bitjoka Vincent
 */
@Entity
public class SpaceTextContent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String spaceName;
	private String motto;
	@Lob
	private String description;

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}


	public Long getId() {
		return id;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String textArea) {
		this.description = textArea;
	}

}

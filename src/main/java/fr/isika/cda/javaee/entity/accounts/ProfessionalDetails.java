package fr.isika.cda.javaee.entity.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Yoann FRANCOIS
 *
 */
@Entity
@Table(name ="professional_details")
public class ProfessionalDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name = "coach_certification")
	private String coachCertification;
	@Column(name = "coach_form")
	private String coachForm;
	@Column(name = "coach_cv")
	private String coachCV;
	
	//***************GETTERS & SETTERS***************
	public String getCoachCertification() {
		return coachCertification;
	}
	public void setCoachCertification(String coachCertification) {
		this.coachCertification = coachCertification;
	}
	public String getCoachForm() {
		return coachForm;
	}
	public void setCoachForm(String coachForm) {
		this.coachForm = coachForm;
	}
	public String getCoachCV() {
		return coachCV;
	}
	public void setCoachCV(String coachCV) {
		this.coachCV = coachCV;
	}
	public Long getId() {
		return id;
	}
	
}

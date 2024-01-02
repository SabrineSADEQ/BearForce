package fr.isika.cda.javaee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="profesional_details")
public class ProfesionalDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String coachCertification;
	private String coachForm;
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

}

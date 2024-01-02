package fr.isika.cda.javaee.entity.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import fr.isika.cda.javaee.entity.platform.PlatformConfiguration;

@Entity
public class SuperAdmin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String sAFirstName;
	private String sALastName;
	private String sAEmail;
	private String sAPassword;
	
	@OneToOne
	private PlatformConfiguration platformConfiguration;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getsAFirstName() {
		return sAFirstName;
	}
	public void setsAFirstName(String sAFirstName) {
		this.sAFirstName = sAFirstName;
	}
	public String getsALastName() {
		return sALastName;
	}
	public void setsALastName(String sALastName) {
		this.sALastName = sALastName;
	}
	public String getsAEmail() {
		return sAEmail;
	}
	public void setsAEmail(String sAEmail) {
		this.sAEmail = sAEmail;
	}
	public String getsAPassword() {
		return sAPassword;
	}
	public void setsAPassword(String sAPassword) {
		this.sAPassword = sAPassword;
	}

}

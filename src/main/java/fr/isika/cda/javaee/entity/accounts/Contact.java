package fr.isika.cda.javaee.entity.accounts;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Yoann FRANCOIS
 *
 */
@Entity
@Table(name ="contact")
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String phone;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private TypeContact type;
	
	@OneToOne
	private Profile profile;
	
	//***************GETTERS & SETTERS***************
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TypeContact getType() {
		return type;
	}
	public void setType(TypeContact type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	
}

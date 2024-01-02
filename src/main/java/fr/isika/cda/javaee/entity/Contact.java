package fr.isika.cda.javaee.entity;

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
@Table(name ="contact")
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String phone;
	private String email;
	private TypeContact type;
	
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

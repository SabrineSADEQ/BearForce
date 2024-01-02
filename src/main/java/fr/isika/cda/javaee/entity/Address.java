package fr.isika.cda.javaee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="address")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String streetNulmber;
	private String streetName;
	private String postalCode;
	private String city;
	
	//***************GETTERS & SETTERS***************
	public String getStreetNulmber() {
		return streetNulmber;
	}
	public void setStreetNulmber(String streetNulmber) {
		this.streetNulmber = streetNulmber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}

package fr.isika.cda.javaee.entity.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name ="address")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name = "street_number")
	private String streetNumber;
	@Column(name = "street_name")
	private String streetName;
	@Column(name = "postal_code")
	private String postalCode;
	private String city;
	@OneToOne
	private Profile profile;
	
	//***************GETTERS & SETTERS***************
	public String getStreetNulmber() {
		return streetNumber;
	}
	public void setStreetNulmber(String streetNulmber) {
		this.streetNumber = streetNulmber;
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
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public Long getId() {
		return id;
	}	
	
}

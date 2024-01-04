package fr.isika.cda.javaee.entity.accounts;

import java.time.LocalDate;
import javax.persistence.CascadeType;
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
@Table(name ="profile")
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	@Column(name = "picture_url")
	private String pictureUrl;
	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact = new Contact();
	@OneToOne(cascade = CascadeType.ALL)
	private Address address = new Address();
	@OneToOne(cascade = CascadeType.ALL)
	private ProfessionalDetails profesionalDetails = new ProfessionalDetails();
	
	@OneToOne
	  private Account account;

	    public Account getAccount() {
		return account;
	}


		public void setAccount(Account account) {
	        this.account = account;
	    }

	
	//***************GETTERS & SETTERS***************
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ProfessionalDetails getProfesionalDetails() {
		return profesionalDetails;
	}
	public void setProfesionalDetails(ProfessionalDetails profesionalDetails) {
		this.profesionalDetails = profesionalDetails;
	}
	public Long getId() {
		return id;
	}
	
}

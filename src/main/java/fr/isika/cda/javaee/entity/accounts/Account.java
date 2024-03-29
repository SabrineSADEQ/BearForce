package fr.isika.cda.javaee.entity.accounts;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.criteria.Predicate.BooleanOperator;

import fr.isika.cda.javaee.entity.gymspace.business.Booking;
import fr.isika.cda.javaee.entity.platform.Subscription;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 150, unique = true, nullable = false)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
	private Goal goal;

	@OneToOne
	private Subscription subscription;

	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")

	private List<Booking> bookingsList = new ArrayList<>();

	private Long gymId;
	

	public Long getGymId() {

		return gymId;
	}

	public void setGymId(Long gymId) {
		this.gymId = gymId;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	private boolean connected;

	private int wizardStep;

	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Account() {
	}

	public Account(Long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public int getWizardStep() {
		return wizardStep;
	}

	public void setWizardStep(int wizardStep) {
		this.wizardStep = wizardStep;
	}

	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}


	public List<Booking> getBookingsList() {
		return bookingsList;
	} 
	
	public void setBookingsList(List<Booking> bookingsList) {
		this.bookingsList = bookingsList;
	}	

}

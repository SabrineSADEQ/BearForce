package fr.isika.cda.javaee.entity.gymspace;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.isika.cda.javaee.entity.gymspace.business.PaymentReceipt;

@Entity
@Table(name = "memberShip")

public class Membership {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private double price;
	@Column(name = "nb_of_activities")
	private int nbrOfActivities;
	@Column(name = "description")
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	private IdGym idGym = new IdGym();

	@OneToOne(cascade = CascadeType.ALL)
	private PaymentReceipt paymentReceipt = new PaymentReceipt();

	public Membership() {
	}

	public Membership(String name, double price, int nbrOfActivities, String description) {
		this.name = name;
		this.price = price;
		this.nbrOfActivities = nbrOfActivities;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNbrOfActivities() {
		return nbrOfActivities;
	}

	public void setNbrOfActivities(int nbrOfActivities) {
		this.nbrOfActivities = nbrOfActivities;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

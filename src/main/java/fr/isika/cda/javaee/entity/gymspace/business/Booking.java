package fr.isika.cda.javaee.entity.gymspace.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.isika.cda.javaee.entity.accounts.Account;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private List<Course> coursesList = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private List<Account> accountsList = new ArrayList<>();
	
}

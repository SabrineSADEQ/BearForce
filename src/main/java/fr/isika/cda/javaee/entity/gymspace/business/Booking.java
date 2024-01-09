package fr.isika.cda.javaee.entity.gymspace.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import fr.isika.cda.javaee.entity.accounts.Account;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	private Course course;
	@ManyToOne
	private Account account;
	
	
	//**************GETTERS & SETTERS**************
	public long getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
		
}

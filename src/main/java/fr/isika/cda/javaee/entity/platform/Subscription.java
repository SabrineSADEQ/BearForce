package fr.isika.cda.javaee.entity.platform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.Membership;

@Entity
@Table(name = "Subscription")

public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "start_Date")
	private LocalDateTime startDate;
	@Column(name = "end_Date")
	private LocalDateTime endDate;
	@Column(name = "auto_Renewal")
	private boolean autoRenewal;
	@Column(name = "duration")
	private int duration;

	@ManyToOne
	private Membership membership = new Membership ();

	
	public Membership getMembership() {
		return membership;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	public Subscription() {
	}

	public Subscription(LocalDateTime startDate, LocalDateTime endDate, boolean autoRenewal, int duration) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.autoRenewal = autoRenewal;
		this.duration = duration;
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime localDateTime) {
		this.startDate = localDateTime;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime localDateTime) {
		this.endDate = localDateTime;
	}

	public boolean isAutoRenewal() {
		return autoRenewal;
	}

	public void setAutoRenewal(boolean autoRenewal) {
		this.autoRenewal = autoRenewal;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setMembership(Membership membershipChoisi) {
		this.membership = membershipChoisi;
		
	}

	

	

}

package fr.isika.cda.javaee.entity.platform;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.isika.cda.javaee.entity.gymspace.Membership;

@Entity
@Table(name = "Subscription")

public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "start_Date")
	private LocalTime startDate;
	@Column(name = "end_Date")
	private LocalTime endDate;
	@Column(name = "auto_Renewal")
	private boolean autoRenewal;
	@Column(name = "duration")
	private int duration;

	@OneToOne(cascade = CascadeType.ALL)
	private Membership membership;

	public Subscription() {
	}

	public Subscription(LocalTime startDate, LocalTime endDate, boolean autoRenewal, int duration) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.autoRenewal = autoRenewal;
		this.duration = duration;
	}

	public long getId() {
		return id;
	}

	public LocalTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalTime startDate) {
		this.startDate = startDate;
	}

	public LocalTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalTime endDate) {
		this.endDate = endDate;
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

}

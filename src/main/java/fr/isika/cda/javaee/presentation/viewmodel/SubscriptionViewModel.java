package fr.isika.cda.javaee.presentation.viewmodel;

import java.util.Date;

public class SubscriptionViewModel<DateTime> {
	private Long id;
	private DateTime startDate;
	private DateTime endDate;
	private boolean autorenewal;
	private int duration;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	public DateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	public boolean isAutorenewal() {
		return autorenewal;
	}
	public void setAutorenewal(boolean autorenewal) {
		this.autorenewal = autorenewal;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	

}

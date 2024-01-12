package fr.isika.cda.javaee.presentation.viewmodel;

import java.time.LocalDate;

/**
 * @author Bitjoka Vincent
 */
public class SubscriptionViewModel {

	private LocalDate startDate;
	private LocalDate endDate;
	private boolean autorenewal;
	private int duration;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}

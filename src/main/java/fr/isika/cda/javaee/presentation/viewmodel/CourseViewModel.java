package fr.isika.cda.javaee.presentation.viewmodel;

import java.time.LocalDateTime;

public class CourseViewModel {
	
	private long id;
	private long activityId;
	private LocalDateTime startDate ;
	private LocalDateTime endDate ;
	private long trainerId;
	private int nbPlaces;
	
	//***************GETTERS & SETTERS***************
	public long getActivityId() {
		return activityId;
	}
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	public long getId() {
		return id;
	}
	

	
}

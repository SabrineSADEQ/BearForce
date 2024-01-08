package fr.isika.cda.javaee.entity.gymspace.business;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import fr.isika.cda.javaee.entity.accounts.Profile;

/** 
 * Represents a course which is an activity with a start and end DateTime, a coach and a number of places 
 * @author Floriane D. 
 */

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;	
	@ManyToOne(fetch = FetchType.LAZY)
	private Activity activity;	
	@Column(name="start_date")
	private LocalDateTime startDate ;	
	@Column(name="end_date")
	private LocalDateTime endDate ;	
	@OneToOne
	private Profile trainer;	
	@Column(name="nb_places")
	private int nbPlaces ;
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public Profile getTrainer() {
		return trainer;
	}

	public void setTrainer(Profile trainer) {
		this.trainer = trainer;
	}
	
	public long getId() {
		return id;
	}
	
	
}

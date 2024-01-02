package fr.isika.cda.javaee.entity.accounts;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String goalName;
	private LocalDate goalDate;
	
	private Double goalValue;

	public Goal() {
		// Default constructor
	}

	public Goal(String goalName, LocalDate goalDate, double goalValue) {
		this.goalName = goalName;
		this.goalDate = goalDate;
		this.goalValue = goalValue;
	}

	public Long getId() {
		return id;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public LocalDate getGoalDate() {
		return goalDate;
	}

	public void setGoalDate(LocalDate goalDate) {
		this.goalDate = goalDate;
	}

	public double getGoalValue() {
		return goalValue;
	}

	public void setGoalValue(double goalValue) {
		this.goalValue = goalValue;
	}


	// Override toString() if needed (use StringBuilder/StringBuffer for performance issues and clean code)
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goal [id=");
		builder.append(id);
		builder.append(", goalName=");
		builder.append(goalName);
		builder.append(", goalDate=");
		builder.append(goalDate);
		builder.append(", goalValue=");
		builder.append(goalValue);
		builder.append("]");
		return builder.toString();
	}

}

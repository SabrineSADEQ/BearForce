package fr.isika.cda.javaee.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Goal {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String goalName;
	    private LocalDate goalDate;
	    private double goalValue;

	    // Constructors, getters, and setters

	    public Goal() {
	        // Default constructor
	    }

	    public Goal(String goalName, LocalDate goalDate, double goalValue) {
	        this.goalName = goalName;
	        this.goalDate = goalDate;
	        this.goalValue = goalValue;
	    }

	    // Other constructors if needed

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


	    // Override toString() if needed

	    @Override
	    public String toString() {
	        return "Goal{" +
	                "id=" + id +
	                ", goalName='" + goalName + '\'' +
	                ", goalDate='" + goalDate + '\'' +
	                ", goalValue=" + goalValue +
	                '}';
	    }

}

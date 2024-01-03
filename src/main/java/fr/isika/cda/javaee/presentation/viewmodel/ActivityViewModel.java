package fr.isika.cda.javaee.presentation.viewmodel;

import java.util.List;
import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;

public class ActivityViewModel {
	
	private ActivityDao activityDao;
	private Activity newActivity = new Activity();
	private List<Activity> activities;
	
	public ActivityViewModel(ActivityDao activityDao) {
		this.activityDao = activityDao;
		refreshActivitiesList();		
	}
	
	public void addActivity() {
		newActivity = new Activity();
		refreshActivitiesList();
	}
	
	private void refreshActivitiesList() {
		activities = activityDao.getAllActivities();
	}

	//***************GETTERS & SETTERS**************
	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public Activity getNewActivity() {
		return newActivity;
	}

	public void setNewActivity(Activity newActivity) {
		this.newActivity = newActivity;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	

}

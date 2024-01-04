package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.ActivityCategory;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityViewModel;

@Named
@ViewScoped
public class ActivityController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ActivityViewModel activityViewModel = new ActivityViewModel();
	
	@Inject
	private ActivityDao activityDao;
	
	@PostConstruct
	public void init() {
		System.out.println("ActivityController bean initialized!");
	}

	public void addActivity() {
		activityDao.createActivity(activityViewModel);
		activityViewModel = new ActivityViewModel();
	}
	
	public List<Activity> displayActivitiesList() {
		return activityDao.getAllActivities();
	}
	
	//!!!!!!!!!!!!!!ATTENTION A METTRE DANS PROFILECONTROLLER!!!!!!!!!!!!!!
	public List<Profile> displayTrainersList() {
		return activityDao.getAllTrainer();
	}

	//***************GETTERS & SETTERS***************
	public ActivityViewModel getActivityViewModel() {
		return activityViewModel;
	}

	public void setActivityViewModel(ActivityViewModel activityViewModel) {
		this.activityViewModel = activityViewModel;
	}
	
	//***************FOR ENUM VALUES***************
	public ActivityCategory[] getActivityCategories() {
		return ActivityCategory.values();
	}
	
}

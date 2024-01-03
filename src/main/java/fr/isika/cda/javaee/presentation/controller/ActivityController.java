package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.presentation.viewmodel.ActivityViewModel;

@Named
@RequestScoped
public class ActivityController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ActivityViewModel activityViewModel;
	@Inject
	private ActivityDao activityDao;
	
	@PostConstruct
	public void init() {
		activityViewModel = new ActivityViewModel(activityDao);
	}
	
	public ActivityViewModel getActivityViewModel() {
		return activityViewModel;
	}

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public void setActivityViewModel(ActivityViewModel activityViewModel) {
		this.activityViewModel = activityViewModel;
	}
	
	
	

}

package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
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
	private List<Long> selectedEquipments = new ArrayList<>();
	private Activity selectedActivity;

	@Inject
	private ActivityDao activityDao;

	@PostConstruct
	public void init() {
		System.out.println("ActivityController bean initialized!");
	}

	public void addActivity() {
		activityDao.createActivity(activityViewModel, selectedEquipments);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Une nouvelle activité nommée '" + activityViewModel.getName() + "' a été enregistrée."));	
		resetInputData();
	}

	private void resetInputData() {
		activityViewModel = new ActivityViewModel();
		selectedEquipments.clear();
	}
	
	//DISPLAY COACHS LIST
	public List<Profile> displayTrainersList() {
		return activityDao.getAllTrainers();
	}

	public List<Activity> displayActivitiesList(){
		return activityDao.getAllActivities();
	}

	//DELETE ACTIVITY FROM DATABASE
	public void deleteSelectedActivity() {
		activityDao.deleteActivity(selectedActivity.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Activité supprimée"));
	}
	
	//MODIFIE ACTIVITY IN DATABASE
	public void modifieSelectedActivity() {
		activityDao.updateActivity(selectedActivity);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Activité mise à jour"));
		PrimeFaces.current().executeScript("PF('manageActivityDialog').hide()");
	}

	//***************GETTERS & SETTERS***************
	public ActivityViewModel getActivityViewModel() {
		return activityViewModel;
	}

	public void setActivityViewModel(ActivityViewModel activityViewModel) {
		this.activityViewModel = activityViewModel;
	}

	public List<Long> getSelectedEquipments() {
		return selectedEquipments;
	}

	public void setSelectedEquipments(List<Long> selectedEquipments) {
		this.selectedEquipments = selectedEquipments;
	}

	public Activity getSelectedActivity() {
		return selectedActivity;
	}

	public void setSelectedActivity(Activity selectedActivity) {
		this.selectedActivity = selectedActivity;
	}

	//***************FOR ENUM VALUES***************
	public ActivityCategory[] getActivityCategories() {
		return ActivityCategory.values();
	}

}

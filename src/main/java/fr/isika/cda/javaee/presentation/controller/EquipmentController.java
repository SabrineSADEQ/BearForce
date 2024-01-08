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

import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.dao.EquipmentDao;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.presentation.viewmodel.EquipmentViewModel;

@Named
@ViewScoped
public class EquipmentController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EquipmentViewModel equipmentViewModel = new EquipmentViewModel();
	private List<Long> selectedActivities = new ArrayList<>();
	
	@Inject
	private EquipmentDao equipmentDao;
	
	@Inject
	private ActivityDao activityDao;
	
	@PostConstruct
	public void init() {
		System.out.println("EquipementController bean initialized!");
	}
	//***************METHODS***************
	//SAVE EQUIPMENT IN DATABASE
	public void addEquipement() {
		equipmentDao.createEquipment(equipmentViewModel);
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Un nouvel équipement nommé '" + equipmentViewModel.getEquipmentName() + "' a été enregistré."));
		equipmentViewModel = new EquipmentViewModel();
	}
	
	//GET FROM DATABASE ACTIVITIES LIST	
	public List<Activity> getActivitiesList(){
		return activityDao.getAllActivitiesWithEquipements();
	}

	//***************GETTERS & SETTERS***************
	public EquipmentViewModel getEquipmentViewModel() {
		return equipmentViewModel;
	}

	public void setEquipmentViewModel(EquipmentViewModel equipmentViewModel) {
		this.equipmentViewModel = equipmentViewModel;
	}
	
	public List<Long> getSelectedActivities() {
		return selectedActivities;
	}
	public void setSelectedActivities(List<Long> selectedActivities) {
		this.selectedActivities = selectedActivities;
	}
	
}

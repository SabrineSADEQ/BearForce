package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import fr.isika.cda.javaee.dao.ActivityDao;
import fr.isika.cda.javaee.dao.EquipmentDao;
import fr.isika.cda.javaee.entity.gymspace.business.Activity;
import fr.isika.cda.javaee.entity.gymspace.business.Equipment;
import fr.isika.cda.javaee.presentation.viewmodel.EquipmentViewModel;
import fr.isika.cda.javaee.utils.FileUploadUtils;

@Named
@ViewScoped
public class EquipmentController implements Serializable {

	private static final long serialVersionUID = 1L;

	private EquipmentViewModel equipmentViewModel = new EquipmentViewModel();
	private List<Long> selectedActivities = new ArrayList<>();
	private Long selectedActivity;
	private Equipment selectedEquipment;

	@Inject
	private EquipmentDao equipmentDao;

	@Inject
	private ActivityDao activityDao;

	@PostConstruct
	public void init() {
		System.out.println("EquipementController bean initialized!");
	}
	
	
	//SAVE EQUIPMENT IN DATABASE
	public void addEquipement() {
		equipmentDao.createEquipment(equipmentViewModel, selectedActivity);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Un nouvel équipement nommé '" + equipmentViewModel.getEquipmentName() + "' a été enregistré."));	
		resetInputData();
	}

	//MODIFIE EQUIPMENT IN DATABASE
	public void modifieSelectedEquipment() {
		equipmentDao.updateEquipment(selectedEquipment, selectedActivity);
		equipmentDao.updateEquipmentPicture(selectedEquipment.getId(), equipmentViewModel.getEquipmentPicturePath());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Equipement mise à jour"));
		PrimeFaces.current().executeScript("PF('manageEquipmentDialog').hide()");
	}

	//DELETE EQUIPMENT FROM DATABASE
	public void deleteSelectedEquipment() {
		equipmentDao.deleteEquipment(selectedEquipment.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Equipement supprimée"));
	}

	//DISPLAY EQUIPMENT LIST
	public List<Equipment> displayEquipmentsList(){
		return equipmentDao.getAllEquipmentsWithActivities();
	}

	//DISPLAY EQUIPMENT LIST DEPENDING TO GYMID
	public List<Equipment> displayEquipmentsListByGymId() throws Exception{
		return equipmentDao.getAllEquipmentsOfGymId();
	}

	//RESET DATA IN TEXT AREAS
	private void resetInputData() {
		equipmentViewModel = new EquipmentViewModel();
		selectedActivity = null;
	}

	//GET FROM DATABASE ACTIVITIES LIST	
	public List<Activity> getActivitiesList(){
		return activityDao.getAllActivitiesWithEquipements();
	}

	public List<Equipment> getEquipmentsList() {
		return equipmentDao.getAllUnavailableEquipmentsWithActivities();
	}

	public void openNewEquipment() {
		this.selectedEquipment = new Equipment();
	}
	
	//FOR PICTURES
		public void uploadFileLogo(FileUploadEvent event) throws Exception {
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

			UploadedFile uploadedFile = event.getFile();
			String fileName = String.join("_", timestamp, uploadedFile.getFileName());

			equipmentViewModel.setEquipmentPicturePath(fileName);

			FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
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
	public Long getSelectedActivity() {
		return selectedActivity;
	}
	public void setSelectedActivity(Long selectedActivity) {
		this.selectedActivity = selectedActivity;
	}
	public Equipment getSelectedEquipment() {
		return selectedEquipment;
	}
	public void setSelectedEquipment(Equipment selectedEquipment) {
		this.selectedEquipment = selectedEquipment;
	}

}

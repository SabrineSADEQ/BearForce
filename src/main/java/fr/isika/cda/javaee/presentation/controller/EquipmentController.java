package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import fr.isika.cda.javaee.dao.EquipmentDao;
import fr.isika.cda.javaee.presentation.viewmodel.EquipmentViewModel;

@Named
@ViewScoped
public class EquipmentController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EquipmentViewModel equipmentViewModel = new EquipmentViewModel();
	
	@Inject
	private EquipmentDao equipmentDao;
	
	@PostConstruct
	public void init() {
		System.out.println("EquipementController bean initialized!");
	}
		
	public void addEquipement() {
		equipmentDao.createEquipment(equipmentViewModel);
		equipmentViewModel = new EquipmentViewModel();
	}

	//***************GETTERS & SETTERS***************
	public EquipmentViewModel getEquipmentViewModel() {
		return equipmentViewModel;
	}

	public void setEquipmentViewModel(EquipmentViewModel equipmentViewModel) {
		this.equipmentViewModel = equipmentViewModel;
	}
	

	
	
	
}

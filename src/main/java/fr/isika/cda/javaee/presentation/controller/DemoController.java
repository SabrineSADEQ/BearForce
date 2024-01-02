package fr.isika.cda.javaee.presentation.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.ApplicationDao;
import fr.isika.cda.javaee.entity.ApplicationEntity;
import fr.isika.cda.javaee.presentation.viewmodel.DemoDataViewModel;

@Named
public class DemoController {

	@Inject
	private ApplicationDao appDao;
	
	private DemoDataViewModel appDataViewModel;
	
	@PostConstruct
	public void init() {
		// load test data
		ApplicationEntity applicationEntity = appDao.last();
		
		// map to the view model
		appDataViewModel = new DemoDataViewModel();
		appDataViewModel.setId(applicationEntity.getId());
		appDataViewModel.setName(applicationEntity.getName());
		appDataViewModel.setDescription(applicationEntity.getDescription());
		appDataViewModel.setCreationDate(applicationEntity.getCreationDate());
	}
	
	public DemoDataViewModel getAppDataViewModel() {
		return appDataViewModel;
	}
	public void setAppDataViewModel(DemoDataViewModel appDataViewModel) {
		this.appDataViewModel = appDataViewModel;
	}
	
}

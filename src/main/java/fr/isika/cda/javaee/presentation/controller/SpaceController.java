package fr.isika.cda.javaee.presentation.controller;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

/**
 * Controller for the gym space creation views for a manager
 * @author Floriane D.
 */
@Named
@SessionScoped
public class SpaceController  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private SpaceViewModel spaceViewModel = new SpaceViewModel();
	
	@Inject
	private SpaceDao spaceDao ;
	
	 @PostConstruct
	    public void init() {
	        System.out.println("SpaceController bean initialized!");
	    }
	 
	 public String createSpaceTextContent() {
		 spaceDao.createSpaceTextContent(spaceViewModel);
		 return "spaceVisualIdentityCreation";
	 }
	 
	 public void createSpaceVisualIdentity() {
		 spaceDao.createSpaceVisualIdentity(spaceViewModel);
		 spaceViewModel = new SpaceViewModel();
	 }
	 

	public SpaceViewModel getSpaceViewModel() {
		return spaceViewModel;
	}

	public void setSpaceViewModel(SpaceViewModel spaceViewModel) {
		this.spaceViewModel = spaceViewModel;
	}
	
	

	 
	
	
}

package fr.isika.cda.javaee.presentation.controller;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.entity.gymspace.Space;
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
	 
	 public String createSpace() {
		 spaceDao.createSpace(spaceViewModel);
		 spaceViewModel = new SpaceViewModel();
		 return "spaceIndex";
	 }
	 
	 public Space getSpaceById(Long id) {
		Space space = spaceDao.getSpaceById(id);
		 
		return space ;
	 }

	public SpaceViewModel getSpaceViewModel() {
		return spaceViewModel;
	}

	public void setSpaceViewModel(SpaceViewModel spaceViewModel) {
		this.spaceViewModel = spaceViewModel;
	}
	
	

	 
	
	
}

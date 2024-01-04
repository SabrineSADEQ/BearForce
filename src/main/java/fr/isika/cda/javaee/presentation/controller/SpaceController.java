package fr.isika.cda.javaee.presentation.controller;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;

/**
 * Controller for the gym space creation views for a manager
 * @author Floriane D.
 */
@Named
@ViewScoped
public class SpaceController  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private SpaceViewModel spaceViewModel = new SpaceViewModel();
    
  //  private transient UploadedFile uploadedFile;
    
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
	 
//	 public void upload() {
////		    String fileName = uploadedFile.getFileName();
////		    String contentType = uploadedFile.getContentType();
//		    byte[] contents = uploadedFile.getContent(); 
//		    spaceViewModel.setGymLogo(contents);
//		}
	 
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

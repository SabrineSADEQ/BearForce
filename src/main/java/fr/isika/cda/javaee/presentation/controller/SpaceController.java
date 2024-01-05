package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;
import fr.isika.cda.javaee.utils.FileUploadUtils;

/**
 * Controller for the gym space creation views for a manager
 * 
 * @author Floriane D.
 */
@Named
@ViewScoped
public class SpaceController implements Serializable {

	private static final long serialVersionUID = 1L;

	private SpaceViewModel spaceViewModel = new SpaceViewModel();
	private boolean skip;

	@Inject
	private SpaceDao spaceDao;

	@PostConstruct
	public void init() {
		System.out.println("SpaceController bean initialized!");
	}


	public Space getSpaceById(Long id) {
		Space space = spaceDao.getSpaceById(id);

		return space;
	}

	public String createSpace() {
		spaceDao.createSpace(spaceViewModel);
		spaceViewModel = new SpaceViewModel();
		return "spaceIndex";
	}
	
	public void save() {
        FacesMessage msg = new FacesMessage("Espace de " + spaceViewModel.getSpaceName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
    /**
     * Method for photo upload
     * @param event
     * @throws Exception
     */
    public void uploadFile(FileUploadEvent event) throws Exception {
    	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

    	UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());
        
		spaceViewModel.setGymLogoPath(fileName);

		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
    }


	public SpaceViewModel getSpaceViewModel() {
		return spaceViewModel;
	}

	public void setSpaceViewModel(SpaceViewModel spaceViewModel) {
		this.spaceViewModel = spaceViewModel;
	}


}

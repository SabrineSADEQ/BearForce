package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.component.log.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.account.controller.LoginController;
import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.presentation.viewmodel.SpaceViewModel;
import fr.isika.cda.javaee.utils.FileUploadUtils;

/**
 * Controller for the gym space creation views for a manager
 * 
 * @author Floriane D.
 */
@Named
@SessionScoped
public class SpaceController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String spaceId;
	private SpaceViewModel spaceViewModel = new SpaceViewModel();
	private boolean skip;

	@Inject
	private SpaceDao spaceDao;

	@Inject
	private AccountDao accountDao;

	@PostConstruct
	public void init() {
		System.out.println("SpaceController bean initialized!");
	}

	public Space getLoadedSpace() throws Exception {

		// Get param from url
		Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String spaceIdParam = parameterMap.get("spaceId");
		if (spaceIdParam != null) {
			Space space = spaceDao.getSpaceById(Long.valueOf(spaceIdParam));
			return space;
		}
		else {
			LoginController loginController = new LoginController();
			Space space = spaceDao.getSpaceById(loginController.getLoggedAccount().getGymId());
			return space;
		}
	}

	public List<Space> getAllSpaces() {
		List<Space> spaces = spaceDao.getAllSpaces();
		return spaces;
	}

	public String redirectToSpace(Long spaceId) {
		return "testSpaceTemplate.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}

	public void createSpace() {

		Long spaceId = spaceDao.createSpace(spaceViewModel).getId();
		injectTheIdOfTheSpaceCreatedIntoTheAccountOfTheCreator(spaceId);
		spaceViewModel = new SpaceViewModel();
		redirectToSpace(spaceId);
	}
	
	public void injectTheIdOfTheSpaceCreatedIntoTheAccountOfTheCreator(long spaceId) {

		
		LoginController controller = new LoginController();
		Account logged = controller.getLoggedAccount();
		logged.setGymId(spaceId);

		//accountDao.update(logged);
	}

	public void save() {
		FacesMessage msg = new FacesMessage("Espace de " + spaceViewModel.getSpaceName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	/**
	 * Method for photo upload
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void uploadFileLogo(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());

		spaceViewModel.setGymLogoPath(fileName);

		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

	public void uploadFileBanner(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());

		spaceViewModel.setGymBannerPath(fileName);

		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}

	public SpaceViewModel getSpaceViewModel() {
		return spaceViewModel;
	}

	public void setSpaceViewModel(SpaceViewModel spaceViewModel) {
		this.spaceViewModel = spaceViewModel;
	}

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}

}

package fr.isika.cda.javaee.presentation.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
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
import fr.isika.cda.javaee.utils.SessionUtils;

/**
 * Controller for the gym space creation views for a manager
 * 
 * @author Floriane D.
 */
@Named
@ViewScoped
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

//		// Check if user is in session
//		HttpSession session = SessionUtils.getSession();
//		Account account = (Account) session.getAttribute("loggedInUser");
//		if (account != null) {
//			if (account.getGymId() != null) {
//				// get space id from account session
//				Long spaceId = SessionUtils.getAccount().getGymId();
//				// get space from spaceId if spaceId not null
//				if (spaceId != null) {
//					Space space = spaceDao.getSpaceById(spaceId);
//					chargeSpaceIntoSpaceViewModel(space);
//				}
//			}
//		} else {
//			System.out.println("Attention ! pas de compte dans la session");
		try {
			Space space = getLoadedSpaceDependingOnLoginStatus();
			chargeSpaceIntoSpaceViewModel(space);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// }

	public Space getLoadedSpace() throws Exception {

		// Get param from url
		Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String spaceIdParam = parameterMap.get("spaceId");

		if (spaceIdParam != null) {
			Space space = spaceDao.getSpaceById(Long.valueOf(spaceIdParam));
			return space;
		} else {
			LoginController loginController = new LoginController();
			Space space = spaceDao.getSpaceById(loginController.getLoggedAccount().getGymId());
			return space;
		}
	}

	public Space getLoadedSpaceDependingOnLoginStatus() throws Exception {

		// If user is not logged in,
		// Get spaceId param from url
		HttpSession session = SessionUtils.getSession();
		Account account = (Account) session.getAttribute("loggedInUser");
		if (account == null) {

			Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String spaceIdParam = parameterMap.get("spaceId");

			if (spaceIdParam != null) {
				Space space = spaceDao.getSpaceById(Long.valueOf(spaceIdParam));
				return space;
			}
			throw new Exception("No space id specified");
		}
		// if user is logged in , get spaceId from account stored in session
		else {
			if (account.getGymId() != null) {
				// get space id from account session
				Long spaceId = SessionUtils.getAccount().getGymId();
				// get space from spaceId if spaceId not null
				if (spaceId != null) {
					Space space = spaceDao.getSpaceById(spaceId);
					return space;
				}
			}
		}
		return null;
	}

	public void chargeSpaceIntoSpaceViewModel(Space space) {
		// load the view model with the space informations
		// Text content
		spaceViewModel.setSpaceName(space.getVisualIdentity().getSpaceTextContent().getSpaceName());
		spaceViewModel.setMotto(space.getVisualIdentity().getSpaceTextContent().getMotto());
		spaceViewModel.setDescription(space.getVisualIdentity().getSpaceTextContent().getDescription());

		// Colors
		spaceViewModel.setFirstColor(space.getVisualIdentity().getFirstColor());
		spaceViewModel.setSecondColor(space.getVisualIdentity().getSecondColor());
		spaceViewModel.setThirdColor(space.getVisualIdentity().getThirdColor());

		// Photos
		spaceViewModel.setGymLogoPath(space.getVisualIdentity().getGymLogoPath());
		spaceViewModel.setGymBannerPath(space.getVisualIdentity().getGymBannerPath());

		// Gym caracteristics
		spaceViewModel.setLockerRoom(space.getIdGym().getGymCaracteristics().isLockerRoom());
		spaceViewModel.setFreeAccess(space.getIdGym().getGymCaracteristics().isLibreAccess());

		// Contact info
		spaceViewModel.getContactInfo().setEmail(space.getIdGym().getAdminInfoGym().getContactInfo().getEmail());
		spaceViewModel.getContactInfo().setPhone(space.getIdGym().getAdminInfoGym().getContactInfo().getPhone());
		spaceViewModel.getLocation().setStreetNumber(space.getIdGym().getAdminInfoGym().getLocation().getStreetNumber());
		spaceViewModel.getLocation().setStreetName(space.getIdGym().getAdminInfoGym().getLocation().getStreetName());
		spaceViewModel.getLocation().setPostalCode(space.getIdGym().getAdminInfoGym().getLocation().getPostalCode());
		spaceViewModel.getLocation().setCity(space.getIdGym().getAdminInfoGym().getLocation().getCity());
		
	}

	public List<Space> getAllSpaces() {
		List<Space> spaces = spaceDao.getAllSpaces();
		return spaces;
	}

	public String redirectToSpace(Long spaceId) {
		return "accueil.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}

	@Transactional
	public void createSpace() {

		Long spaceId = spaceDao.createSpace(spaceViewModel).getId();
		injectTheIdOfTheSpaceCreatedIntoTheAccountOfTheCreator(spaceId);
		spaceViewModel = new SpaceViewModel();

		
	
		
		

		// Construct the URL with the specific spaceId
		String url = "http://127.0.0.1:8080/BearForce/spaceAdminDashboard.xhtml?spaceId=" + spaceId;

		// Get the FacesContext and ExternalContext
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		// Redirect to the constructed URL
		try {
			externalContext.redirect(url);
		} catch (IOException e) {

			e.printStackTrace();
		}

		facesContext.responseComplete();


	}

	@Transactional
	public void injectTheIdOfTheSpaceCreatedIntoTheAccountOfTheCreator(long spaceId) {

		LoginController controller = new LoginController();
		Account logged = controller.getLoggedAccount();
		logged.setGymId(spaceId);
		accountDao.update(logged);

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

	public void updateSpace() {
		// get space id from account session
		Long spaceId = SessionUtils.getAccount().getGymId();
		// get space from spaceId if spaceId not null
		if (spaceId != null) {
			Space oldSpace = spaceDao.getSpaceById(spaceId);

			if (oldSpace != null) {
				// if space not null, put the space customization informations from the form in
				// this space

				// Contact
				oldSpace.getIdGym().getAdminInfoGym().getContactInfo()
						.setEmail(spaceViewModel.getContactInfo().getEmail());
				oldSpace.getIdGym().getAdminInfoGym().getContactInfo()
						.setPhone(spaceViewModel.getContactInfo().getPhone());
				// SpaceTextContent
				oldSpace.getVisualIdentity().getSpaceTextContent().setSpaceName(spaceViewModel.getSpaceName());
				oldSpace.getVisualIdentity().getSpaceTextContent().setMotto(spaceViewModel.getMotto());
				oldSpace.getVisualIdentity().getSpaceTextContent().setDescription(spaceViewModel.getDescription());
				// VisualIdentity
				oldSpace.getVisualIdentity().setFirstColor(spaceViewModel.getFirstColor());
				oldSpace.getVisualIdentity().setSecondColor(spaceViewModel.getSecondColor());
				oldSpace.getVisualIdentity().setThirdColor(spaceViewModel.getThirdColor());
				oldSpace.getVisualIdentity().setGymLogoPath(spaceViewModel.getGymLogoPath());
				oldSpace.getVisualIdentity().setGymBannerPath(spaceViewModel.getGymBannerPath());

				// call the update method from spaceDao to persist in database
				spaceDao.update(oldSpace);
			} else {
				System.out.println("Space not found with ID : " + spaceId);
			}

			// else: sysout :"Space not found with ID : ..."
		}
		// else : sysout : "Space ID not found or User not logged in"
		else {
			System.out.println("Space ID not found or User not logged in");
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

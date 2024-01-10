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
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.account.controller.LoginController;
import fr.isika.cda.javaee.dao.SpaceDao;
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
@SessionScoped
public class SpaceController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String spaceId;
	private SpaceViewModel spaceViewModel = new SpaceViewModel();
	private boolean skip;

	@Inject
	private SpaceDao spaceDao;

	@PostConstruct
	public void init() {
		System.out.println("SpaceController bean initialized!");

		// Check if user is in session
		HttpSession session = SessionUtils.getSession();
		Account account = (Account) session.getAttribute("loggedInUser");
		if (account != null) {
			if (account.getGymId() != null) {
				// get space id from account session
				Long spaceId = SessionUtils.getAccount().getGymId();
				// get space from spaceId if spaceId not null
				if (spaceId != null) {
					Space space = spaceDao.getSpaceById(spaceId);

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
					spaceViewModel.getContactInfo()
							.setEmail(space.getIdGym().getAdminInfoGym().getContactInfo().getEmail());
					spaceViewModel.getContactInfo()
							.setPhone(space.getIdGym().getAdminInfoGym().getContactInfo().getPhone());

				}
			}
		} else {
			System.out.println("Attention ! pas de compte dans la session");
		}

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
		throw new Exception("No space id specified");
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

	public List<Space> getAllSpaces() {
		List<Space> spaces = spaceDao.getAllSpaces();
		return spaces;
	}

	public String redirectToSpace(Long spaceId) {
		return "testSpaceTemplate.xhtml?faces-redirect=true&amp;spaceId=" + spaceId;
	}

	public void createSpace() {
		LoginController test = new LoginController();

		Long spaceId = spaceDao.createSpace(spaceViewModel).getId();
		test.getLoggedAccount().setGymId(spaceId);
		spaceViewModel = new SpaceViewModel();
		redirectToSpace(spaceId);
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
				
				//Contact
				oldSpace.getIdGym().getAdminInfoGym().getContactInfo().setEmail(spaceViewModel.getContactInfo().getEmail());
				oldSpace.getIdGym().getAdminInfoGym().getContactInfo().setPhone(spaceViewModel.getContactInfo().getPhone());
				//SpaceTextContent
				oldSpace.getVisualIdentity().getSpaceTextContent().setSpaceName(spaceViewModel.getSpaceName());
				oldSpace.getVisualIdentity().getSpaceTextContent().setMotto(spaceViewModel.getMotto());
				oldSpace.getVisualIdentity().getSpaceTextContent().setDescription(spaceViewModel.getDescription());
				//VisualIdentity
				oldSpace.getVisualIdentity().setFirstColor(spaceViewModel.getFirstColor());
				oldSpace.getVisualIdentity().setSecondColor(spaceViewModel.getSecondColor());
				oldSpace.getVisualIdentity().setThirdColor(spaceViewModel.getThirdColor());
				
				//TODO : 
				//add images update
				
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

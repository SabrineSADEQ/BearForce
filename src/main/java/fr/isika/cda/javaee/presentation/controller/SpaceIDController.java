package fr.isika.cda.javaee.presentation.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.utils.SessionUtils;

import java.io.Serializable;

@Named
public class SpaceIDController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String spaceId;
	
	@Inject
	private SpaceDao spaceDao;


	@PostConstruct
	public void init() {
		// Retrieve the space ID from the cookie during controller initialization
		System.out.println("SpaceController bean initialized!");
		retrieveSpaceIdFromCookie();
		// Call saveSpaceIdToCookie automatically when the controller is initialized
		saveSpaceIdToCookie();
	}

	public void saveSpaceIdToCookie() {
		// Check if the user is not logged in before creating the cookie
		if (!isUserLoggedIn()) {
			// Save the space ID to the cookie
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
			Cookie spaceIdCookie = new Cookie("spaceIdCookie", spaceId);
			response.addCookie(spaceIdCookie);
		}
		else if (isUserLoggedIn()) {
			//if the user is already logged in, get the space id stored in his account
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
			Cookie spaceIdCookie = new Cookie("spaceIdCookie", String.valueOf(SessionUtils.getAccount().getGymId()));
			response.addCookie(spaceIdCookie);
		}
	}

	private String retrieveSpaceIdFromCookie() {
		// Retrieve the space ID from the cookie
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("spaceIdCookie".equals(cookie.getName())) {
					spaceId = cookie.getValue();
					return spaceId;
					
				}
			}
		}
		return null;
	}
	
	//ATTENTION : cette methode etait dans spaceController, la remettre la bas si tout bug !!//////////////////////////////////////////////
	public Space getLoadedSpace() throws Exception {

		// Get param from url
//		Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext()
//				.getRequestParameterMap();
//		String spaceIdParam = parameterMap.get("spaceId");
		
		//Get spaceId from the cookie 
		String spaceIdParam = retrieveSpaceIdFromCookie();
		
		if (spaceIdParam != null) {
			Space space = spaceDao.getSpaceById(Long.valueOf(spaceIdParam));
			return space;
		}
		throw new Exception("No space id specified");
	}
	
	public void deleteSpaceIdCookie() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        Cookie[] cookies = ((HttpServletRequest) externalContext.getRequest()).getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("spaceIdCookie".equals(cookie.getName())) {
                    cookie.setMaxAge(0); // Set the cookie's max age to 0 for deletion
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
	

	private boolean isUserLoggedIn() {
		// check if the user is logged in
		// Return true if the user is logged in, false otherwise
		if (SessionUtils.getAccount() != null) {
			return true;
		}
		else if (SessionUtils.getAccount() == null) {
			return false; 
		}
		else {
			return false;	
		}
		
	}

	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
}
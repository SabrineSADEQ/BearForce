package fr.isika.cda.javaee.presentation.controller;


import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.SpaceDao;

/**
 * Controller for the gym space creation views for a manager
 * @author Floriane D.
 */
@Named
public class SpaceController {
	
	@Inject
	private SpaceDao spaceDao ;
	
	
	
	

	public SpaceDao getSpaceDao() {
		return spaceDao;
	}

	public void setSpaceDao(SpaceDao spaceDao) {
		this.spaceDao = spaceDao;
	}
	
	
}

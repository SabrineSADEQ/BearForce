package fr.isika.cda.javaee.presentation.controller;


import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.SpaceDao;
import fr.isika.cda.javaee.entity.gymspace.Space;

@Named
@SessionScoped
public class SpaceVisualController implements Serializable {

	private static final long serialVersionUID = 1L;
    private String firstColor;
    private String secondColor;
    private String thirdColor;

    @Inject
	private SpaceDao spaceDao;

    // Method to fetch space colors from the database
    public void fetchSpaceColors(Long spaceId) {
        // Fetch space colors from the database and set them to the properties
        Space space = spaceDao.getSpaceById(spaceId);
        firstColor = space.getVisualIdentity().getFirstColor();
        secondColor = space.getVisualIdentity().getSecondColor();
        thirdColor = space.getVisualIdentity().getThirdColor();
    }
    
    
    // Getters and setters
	public String getFirstColor() {
		return firstColor;
	}

	public void setFirstColor(String firstColor) {
		this.firstColor = firstColor;
	}

	public String getSecondColor() {
		return secondColor;
	}

	public void setSecondColor(String secondColor) {
		this.secondColor = secondColor;
	}

	public String getThirdColor() {
		return thirdColor;
	}

	public void setThirdColor(String thirdColor) {
		this.thirdColor = thirdColor;
	}
}
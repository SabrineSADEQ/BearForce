package fr.isika.cda.javaee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity 
public class VisualIdentity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String gymLogoURL;
	private String bannerURL;
	private String firstColor;
	private String secondColor;
	private String thirdColor;
	
	@OneToOne
	@JoinColumn(name = "space_id")
	private Space space;
	
	@OneToOne
	private Template template;
	
	@OneToOne
	private SpaceTextContent spaceTextContent;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGymLogoURL() {
		return gymLogoURL;
	}
	public void setGymLogoURL(String gymLogoURL) {
		this.gymLogoURL = gymLogoURL;
	}
	public String getBannerURL() {
		return bannerURL;
	}
	public void setBannerURL(String bannerURL) {
		this.bannerURL = bannerURL;
	}
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

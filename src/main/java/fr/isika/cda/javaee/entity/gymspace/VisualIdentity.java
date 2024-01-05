package fr.isika.cda.javaee.entity.gymspace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
/**
 * 
 * @author Bitjoka Vincent
 */
@Entity 
public class VisualIdentity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String gymLogoPath;
	private String bannerPath;
	private String firstColor;
	private String secondColor;
	private String thirdColor;
	
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
	
	public String getGymLogoPath() {
		return gymLogoPath;
	}
	public void setGymLogoPath(String gymLogoPath) {
		this.gymLogoPath = gymLogoPath;
	}
	public String getBannerPath() {
		return bannerPath;
	}
	public void setBannerPath(String bannerPath) {
		this.bannerPath = bannerPath;
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

	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public SpaceTextContent getSpaceTextContent() {
		return spaceTextContent;
	}
	public void setSpaceTextContent(SpaceTextContent spaceTextContent) {
		this.spaceTextContent = spaceTextContent;
	}
	

}

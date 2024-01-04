package fr.isika.cda.javaee.entity.platform;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import fr.isika.cda.javaee.entity.accounts.SuperAdmin;
/**
 * 
 * @author Bitjoka Vincent
 */
@Entity
public class PlatformConfiguration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String platformName;
	private String logoURL;
	@OneToOne
	@JoinColumn(name = "superAdmin_id")
	private SuperAdmin superAdmin;
	
	@OneToMany
	private List<Pack>packs = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getLogoURL() {
		return logoURL;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	
	

}

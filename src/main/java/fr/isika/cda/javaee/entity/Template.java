package fr.isika.cda.javaee.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Template {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String template;
	
	@OneToMany
	@JoinColumn(name = "visualIdentity")
	private List<VisualIdentity>visualIdentitys = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	

}

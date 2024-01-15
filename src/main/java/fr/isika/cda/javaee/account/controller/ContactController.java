package fr.isika.cda.javaee.account.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.ContactDao;
import fr.isika.cda.javaee.dao.PlatformConfigurationDao;
import fr.isika.cda.javaee.entity.accounts.Contact;
import fr.isika.cda.javaee.entity.platform.PlatformConfiguration;




@Named
@ViewScoped
public class ContactController implements Serializable{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contactId;
	private Contact contact = new Contact();
	@Inject
	private ContactDao contactDao;

	@PostConstruct
	public void init() {
		System.out.println("Contact bean initialized!");
		
	}
	
	 public void loadContactById() {
	        // Assurez-vous que l'ID du contact est non nul avant de le charger
	        if ( contactId!= null) {
	            
	            contact = contactDao.getContactById(contactId);
	        }

}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	 
	 
}

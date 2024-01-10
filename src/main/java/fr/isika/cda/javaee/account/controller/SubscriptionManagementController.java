package fr.isika.cda.javaee.account.controller;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.PackDao;
import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.platform.Pack;
import fr.isika.cda.javaee.utils.SessionUtils;



@Named
@ViewScoped
public class SubscriptionManagementController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PackDao packDao;
	


	private String message;

	
	
	public String paymentPack() {
		
		
		return "pagePayment.xhtml?faces-redirect=true";	
		
	}
	
public String confirmationPayment() {
		
		
		return "ConfirmationPayment.xhtml?faces-redirect=true";	
		
	}

	
	public String dirigeVersSpaceCreation() {
		
		return "spaceCreation.xhtml?faces-redirect=true";
		
	}	
	
	
	
	public List<Pack> listePacks() {
		return packDao.getPacksWithSubscriptions();
	}

	public String getMessage() {
		return message;
	}
}

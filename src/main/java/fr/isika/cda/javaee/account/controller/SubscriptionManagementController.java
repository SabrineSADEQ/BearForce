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
	
	@Inject
	private AccountDao accountDao;

	private String message;

	/** Cette méthode affiche la page de Souscription (avec la liste des packs)
	 * @return
	 */
	public String show(Long packId) {
		// si user connecté => subscription
		Account connectedAccount = SessionUtils.getAccount();
		if(connectedAccount != null) {
			
			Pack pack = packDao.getPack(packId);
			
			// url avec paramètres
			return "souscrireNouveauPack.xhtml?packId="+ pack.getId() 
				+ "&amp;accountId="+ connectedAccount.getId();
		} else {
			// sinon => redirect to CreationDeCompte.xhtml
			return "CreationDeCompte.xhtml?faces-redirect=true";
		}
	}

	/**
	 * Cette méthode doit créer véritable la souscription
	 */
	public String subscribe() {
		
		Map<String,String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String packIdParam = map.get("packId");
		String accountIdParam = map.get("accountId");

		Account account = accountDao.getById(Long.valueOf(accountIdParam));
		Pack pack = packDao.getPack(Long.valueOf(packIdParam));
		
		this.message = "Le user :" + account.getEmail() + " veut souscrire au pack : " + pack.getName();
		System.out.println("Le user :" + account.getEmail() + " veut souscrire au pack : " + pack.getName());
		
		// ici la suite de la tâche "s'abonner à un pack donné ??"
		return message;
	}
	
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

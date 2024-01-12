package fr.isika.cda.javaee.account.controller;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import fr.isika.cda.javaee.dao.accounts.LoginDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.accounts.Role;
import fr.isika.cda.javaee.presentation.controller.SpaceController;
import fr.isika.cda.javaee.utils.SessionUtils;
import fr.isika.cda.javaee.viewModel.AccountViewModel;


@Named
@javax.enterprise.context.SessionScoped
//Serializable : les instances de cette classe peuvent être sérialisées 
//(converties en un flux d'octets) pour être stockées dans un flux, tel qu'une session HTTP.
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	private AccountViewModel accountVM = new AccountViewModel();
	
	@Inject
	private LoginDao loginDao;

	public void validateAccount() {
		boolean isValid = loginDao.validate(accountVM);
		if (isValid) {
			Account account = loginDao.findAccountByEmailAndPassword(accountVM, accountVM.getEmail(), accountVM.getPassword());
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("loggedInUser", account);
			
			 FacesContext facesContext = FacesContext.getCurrentInstance();
		        ExternalContext externalContext = facesContext.getExternalContext();
		        try {
					externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Email ou Mot de passe incorrects", "Merci de saisir les bons identifiants"));
			
		}
	}
	
	public void redirectToCreateAccount() {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
	        ExternalContext externalContext = facesContext.getExternalContext();
	        try {
				externalContext.redirect(externalContext.getRequestContextPath() + "/CreationDeCompte.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public AccountViewModel getAccountVM() {
		return accountVM;
	}

	public void setAccountVM(AccountViewModel accountVM) {
		this.accountVM = accountVM;
	}

	public Account getLoggedAccount() {
		return SessionUtils.getAccount();
	}
	
	public boolean isCoach() {
		return SessionUtils.getAccount() != null 
				&&  Role.COACH.equals(SessionUtils.getAccount().getRole());
	}
	public boolean isAdherent() {
		return SessionUtils.getAccount() != null 
				&&  Role.ADHERENT.equals(SessionUtils.getAccount().getRole());
	}
	public boolean isGestionnaire() {
		return SessionUtils.getAccount() != null 
				&&  Role.GESTIONNAIRE.equals(SessionUtils.getAccount().getRole());
	}
	public boolean isVisiteur() {
		return SessionUtils.getAccount() != null 
				&&  Role.VISITEUR.equals(SessionUtils.getAccount().getRole());
	}
	public boolean isSuperAdmin() {
		return SessionUtils.getAccount() != null 
				&&  Role.SUPER_ADMIN.equals(SessionUtils.getAccount().getRole());
	}
	
	public boolean isLoggedIn() {
		return SessionUtils.getAccount() != null;
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // Add a parameter to the URL indicating a redirect
        externalContext.getFlash().put("refresh", "true");

        // Redirect to the index page
        return "index?faces-redirect=true";
	}
}
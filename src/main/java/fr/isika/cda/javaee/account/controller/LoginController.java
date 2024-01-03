package fr.isika.cda.javaee.account.controller;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import fr.isika.cda.javaee.dao.accounts.LoginDao;
import fr.isika.cda.javaee.viewModel.AccountViewModel;


@ManagedBean
@SessionScoped

//Serializable : les instances de cette classe peuvent être sérialisées 
//(converties en un flux d'octets) pour être stockées dans un flux, tel qu'une session HTTP.
public class LoginController implements Serializable {

	// Attributs
	private static final long serialVersionUID = 1L;

	private AccountViewModel accountVM = new AccountViewModel();
	@Inject
	private LoginDao loginDao;

	private boolean loggedIn = false;


	public LoginController() {
	}

	public void validateAccount() {
		boolean isValid = loginDao.validate(accountVM);
		Account account = loginDao.findAccountByEmailAndPassword(accountVM, accountVM.getEmail(),
				accountVM.getPassword());

		// Si les informations de connexion sont valides
		if (isValid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("loggedInUser", account);
			loggedIn = true;
			// Récupérer l'URL de redirection
			String redirectionUrl = redirectionDashboard();

			// Recuperer les données de la session

			// SessionUtils.writeInSessionTP(tp.getSiret(), tp.getPhoneNumber(),
			// tp.getCompanyName());
			;
			// TODO Ajouter travel planner dans la session

			if (account.getRole() == RoleUser.TravelPlanner) {
				String userEmail = SessionUtils.getAccount().getEmail();
				TravelPlanner tp = loginDao.findTravelPlanner(userEmail);
				if (tp.getTemplate() != null) {
					Template userTemplate = tp.getTemplate();
					HttpSession session1 = SessionUtils.getSession();
					session1.setAttribute("template", userTemplate);
					templateControllerBean.setTemplate(userTemplate);
					templateControllerBean.setSelectedColor(userTemplate.getBackgroundColor());
				}

			}

			// Effectuer la redirection
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(redirectionUrl);
			} catch (IOException e) {
				// Gérer l'exception en cas d'erreur de redirection
				e.printStackTrace();
			}
		} else

		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Email ou Mot de pass incorrectes", "Merci de saisir les bons identifiants"));
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect("signIn.xhtml");
			} catch (IOException e) {
				// Gérer l'exception en cas d'erreur de redirection
				e.printStackTrace();
			}
		}
	}
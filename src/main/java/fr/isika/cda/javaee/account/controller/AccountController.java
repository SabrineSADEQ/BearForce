package fr.isika.cda.javaee.account.controller;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.dao.accounts.LoginDao;
import fr.isika.cda.javaee.entity.accounts.Account;

import fr.isika.cda.javaee.utils.FileUploadUtils;

import fr.isika.cda.javaee.entity.gymspace.Space;

import fr.isika.cda.javaee.utils.SessionUtils;
import fr.isika.cda.javaee.viewModel.AccountViewModel;

@Named
@ViewScoped
public class AccountController implements Serializable {

	private static final long serialVersionUID = 1L;

    private AccountViewModel accountVM = new AccountViewModel();
    private int wizardStep = 1; // Initialize the wizard step to 1
    private boolean wizardMode = true; // Set initial wizard mode to true

	@Inject
	private AccountDao accountDao;

	@PostConstruct
	public void init() {
		System.out.println("AccountController bean initialized!");
		
		// Check if user is in session
		HttpSession session = SessionUtils.getSession();
		Account account = (Account) session.getAttribute("loggedInUser");
		if(account != null) {
			// TODO : charger toutes les infos du compte connecté et les copier dans le accountVM, comme ci-dessous
			accountVM.getProfile().setFirstName(account.getProfile().getFirstName());
			accountVM.getProfile().setPictureUrl(account.getProfile().getPictureUrl());
			
			
			//Filling the viewmodel for the "Professional details" view
			if (account.getProfile().getProfesionalDetails() != null) {
				accountVM.getProfessionalDetails().setCoachCertification(account.getProfile().getProfesionalDetails().getCoachCertification());
				accountVM.getProfessionalDetails().setCoachForm(account.getProfile().getProfesionalDetails().getCoachForm());
				accountVM.getProfessionalDetails().setCoachCV(account.getProfile().getProfesionalDetails().getCoachCV());
			
			}
			
		} else {
			System.out.println("Attention ! Pas de compte dans la session");
		}
		
	}

    public String addAccount() {
    	accountDao.createAccount(accountVM);

	    // Reset the wizard step and mode for the next user
	    wizardStep = 1;
	    wizardMode = false;
	    
	    
	    accountVM = new AccountViewModel();
    	   
	    // redirection dynamique vers la page + changement diurl 
	   return "CreationDeCompte.xhtml?faces-redirect=true";
    }

    public void nextWizardStep() {
       
        wizardStep++;
    }

    public void previousWizardStep() {

        wizardStep--;
    }

    public void finishWizard() {

        addAccount();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compte créé !", "Account creation successful!"));

        // Reset the wizard step and mode for the next user
        wizardStep = 1;
        wizardMode = false;

        
        redirectToIndex();
    }
    
    public void redirectToIndex() {

        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler handler = context.getApplication().getNavigationHandler();
        handler.handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
        accountVM = new AccountViewModel();
    }
    
    /**
	 * Method for photo upload
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void uploadFileLogo(FileUploadEvent event) throws Exception {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

		UploadedFile uploadedFile = event.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());

		accountVM.getProfile().setPictureUrl(fileName);

		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
	}
    
    public void updateAccount() {
        Account account = SessionUtils.getAccount();
        // Vérifier si l'utilisateur est connecté (authentifié)
        Long accountId = account.getId();

        if (accountId != null) {
            // Retrieve the existing account from the database
            Account existingAccount = accountDao.getAccountById(accountId);

            if (existingAccount != null) {
                // Update the profile attributes
                existingAccount.getProfile().setFirstName(accountVM.getProfile().getFirstName());
                existingAccount.getProfile().setLastName(accountVM.getProfile().getLastName());
                
                existingAccount.getProfile().setPictureUrl(accountVM.getProfile().getPictureUrl());
      
                existingAccount.getProfile().setBirthDate(accountVM.getProfile().getBirthDate());
    			existingAccount.getProfile().setContact(accountVM.getProfile().getContact());
    			existingAccount.getProfile().setAddress(accountVM.getProfile().getAddress());
    			existingAccount.getProfile().setProfesionalDetails(accountVM.getProfile().getProfesionalDetails());
    			existingAccount.setGymId(accountVM.getGymId());
                // Update other account attributes if needed
                existingAccount.setEmail(accountVM.getEmail());

                // Appeler la méthode de mise à jour dans le DAO pour mettre à jour le compte
                accountDao.update(existingAccount);
            } else {
                System.out.println("Compte introuvable pour l'ID : " + accountId);
            }
        } else {
            System.out.println("L'utilisateur n'est pas connecté.");
        }
    }
    
    public void updateCoachProfessionalDetails(){
    	Account account = SessionUtils.getAccount();
        // Vérifier si l'utilisateur est connecté (authentifié)
        Long accountId = account.getId();

        if (accountId != null) {
            // Retrieve the existing account from the database
            Account existingAccount = accountDao.getAccountById(accountId);

            if (existingAccount != null) {
                // Update the professional details attributes
    			existingAccount.getProfile().setProfesionalDetails(accountVM.getProfile().getProfesionalDetails());

                // Appeler la méthode de mise à jour dans le DAO pour mettre à jour le compte
                accountDao.update(existingAccount);
            } else {
                System.out.println("Compte introuvable pour l'ID : " + accountId);
            }
        } else {
            System.out.println("L'utilisateur n'est pas connecté.");
        }
    }
    

    public AccountViewModel getAccountVM() {
        return accountVM;
    }
    
    public List<Account> AllAccounts() {
		List<Account> accounts = accountDao.getAllAccounts();
		return accounts;
	}


    public void setAccountVM(AccountViewModel accountVM) {
        this.accountVM = accountVM;
    }

    public int getWizardStep() {
        return wizardStep;
    }

    public boolean isWizardMode() {
        return wizardMode;
    }

    public void setWizardMode(boolean wizardMode) {
        this.wizardMode = wizardMode;
    }
}


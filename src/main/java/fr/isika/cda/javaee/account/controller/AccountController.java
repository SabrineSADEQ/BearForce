package fr.isika.cda.javaee.account.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.utils.FileUploadUtils;
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
        // Perform any necessary validations before proceeding to the next step
        wizardStep++;
    }

    public void previousWizardStep() {
        // Perform any necessary validations before going back to the previous step
        wizardStep--;
    }

    public void finishWizard() {
        // Perform any necessary validations before finishing the wizard
        // You can save the account details here as well
        addAccount();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compte créer !s", "Account creation successful!"));

        // Reset the wizard step and mode for the next user
        wizardStep = 1;
        wizardMode = false;

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


    public AccountViewModel getAccountVM() {
        return accountVM;
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


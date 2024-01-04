package fr.isika.cda.javaee.account.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.dao.accounts.LoginDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.utils.SessionUtils;
import fr.isika.cda.javaee.viewModel.AccountViewModel;

@Named
@SessionScoped
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

    public void addAccount() {
    	 accountDao.createAccount(accountVM);

    	    // Reset the wizard step and mode for the next user
    	    wizardStep = 1;
    	    wizardMode = false;

    	    accountVM = new AccountViewModel();	
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
package fr.isika.cda.javaee.presentation.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.entity.accounts.Account;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class AccountManagedBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private AccountDao accountDAO;

    private Account loggedInAccount;

    public void login(String email, String password) {
        loggedInAccount = accountDAO.getAccountByEmailAndPassword(email, password);
    }

    public void logout() {
        loggedInAccount = null;
    }

    

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }
}


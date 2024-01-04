package fr.isika.cda.javaee.account.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.annotation.PostConstruct;

import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.viewModel.AccountViewModel;

@Named
@SessionScoped
public class AccountController implements Serializable {

	private static final long serialVersionUID = 1L;

	private AccountViewModel accountVM = new AccountViewModel();

	@Inject
	private AccountDao accountDao;

	@PostConstruct
	public void init() {
		System.out.println("AccountController bean initialized!");
	}

	public void addAccount() {
		accountDao.createAccount(accountVM);
		accountVM = new AccountViewModel();
	}

	public AccountViewModel getAccountVM() {
		return accountVM;
	}

	public void setAccountVM(AccountViewModel accountVM) {
		this.accountVM = accountVM;
	}

}

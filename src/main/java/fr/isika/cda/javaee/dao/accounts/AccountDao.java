package fr.isika.cda.javaee.dao.accounts;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.utils.PasswordUtils;
import fr.isika.cda.javaee.viewModel.AccountViewModel;

@Stateless
public class AccountDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Account createAccount(AccountViewModel accountVM) {
		Account accountbean = new Account();
		accountbean.setEmail(accountVM.getEmail());
		// Hash the password before storing it in the database
		String hashedPassword = PasswordUtils.hashPassword(accountVM.getPassword());
		accountbean.setPassword(hashedPassword);
		accountbean.setRole(accountVM.getRole());
		entityManager.persist(accountbean);
		entityManager.flush();
		return accountbean;
	}

	
	

}
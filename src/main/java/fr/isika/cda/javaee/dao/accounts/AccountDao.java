package fr.isika.cda.javaee.dao.accounts;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.accounts.Profile;
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

	    // Create and set the profile
	    Profile profile = new Profile();
	    profile.setFirstName(accountVM.getProfile().getFirstName());
	    profile.setLastName(accountVM.getProfile().getLastName());
	    
	    profile.setBirthDate(accountVM.getProfile().getBirthDate());
	    profile.setContact(accountVM.getProfile().getContact());
	    profile.setAddress(accountVM.getProfile().getAddress());
	    profile.setProfesionalDetails(accountVM.getProfile().getProfesionalDetails());
	    // Set other profile attributes as needed

	    // Set the profile to the account and vice versa
	    accountbean.setProfile(profile);
	    profile.setAccount(accountbean);

	    // Persist the account and profile
	    entityManager.persist(accountbean);
	    entityManager.persist(profile);

	    // Flush the changes to the database
	    entityManager.flush();

	    return accountbean;


	}

	
	

}
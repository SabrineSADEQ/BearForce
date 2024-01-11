package fr.isika.cda.javaee.dao.accounts;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.gymspace.Space;
import fr.isika.cda.javaee.utils.PasswordUtils;
import fr.isika.cda.javaee.viewModel.AccountViewModel;

@Stateless
public class AccountDao {

	@PersistenceContext
	private EntityManager entityManager;
	private AccountViewModel AccountViewModel;

	public Account createAccount(AccountViewModel accountVM) {
		Account accountbean = new Account();

		accountbean.setEmail(accountVM.getEmail());
		// Hash the password before storing it in the database
		String hashedPassword = PasswordUtils.hashPassword(accountVM.getPassword());
		accountbean.setPassword(hashedPassword);
		accountbean.setRole(accountVM.getRole());
		accountbean.setGymId(accountVM.getGymId());

		// Create and set the profile
		Profile profile = new Profile();
		profile.setFirstName(accountVM.getProfile().getFirstName());
		profile.setLastName(accountVM.getProfile().getLastName());
		profile.setBirthDate(accountVM.getProfile().getBirthDate());
		profile.setPictureUrl(accountVM.getProfile().getPictureUrl());
		profile.setContact(accountVM.getProfile().getContact());
		profile.setAddress(accountVM.getProfile().getAddress());
		profile.setProfesionalDetails(accountVM.getProfile().getProfesionalDetails());

		accountbean.setGoal(accountVM.getGoal());

		// Set other profile attributes as needed

		// Set the profile to the account and vice versa
		accountbean.setProfile(profile);
		profile.setAccount(accountbean);

		// Persist the account and profile
		entityManager.persist(accountbean);
		entityManager.persist(profile);

	

		return accountbean;

	}

	public Account getById(Long accountId) {
		return entityManager.find(Account.class, accountId);
	}

	public Account getAccountByEmailAndPassword(String email, String password) {

		return createAccount(AccountViewModel);
	}

	@Transactional
	public void update(Account updatedAccount) {
		// Rechercher le compte dans la base de données avec l'ID
		Account existingAccount = entityManager.find(Account.class, updatedAccount.getId());

		// Vérifier si le compte existe dans la base de données
		if (existingAccount != null) {
			// Mettre à jour les champs du compte avec les nouvelles valeurs
			existingAccount.getProfile().setFirstName(updatedAccount.getProfile().getFirstName());
			existingAccount.setEmail(updatedAccount.getEmail());
			existingAccount.getProfile().setLastName(updatedAccount.getProfile().getLastName());
			
			existingAccount.getProfile().setBirthDate(updatedAccount.getProfile().getBirthDate());
			existingAccount.getProfile().setContact(updatedAccount.getProfile().getContact());
			existingAccount.getProfile().setAddress(updatedAccount.getProfile().getAddress());
			existingAccount.getProfile().setProfesionalDetails(updatedAccount.getProfile().getProfesionalDetails());
			existingAccount.setGymId(updatedAccount.getGymId());
			// Enregistrer les modifications dans la base de données
			entityManager.merge(existingAccount);
			entityManager.flush();
		} else {
			System.out.println("Compte introuvable pour l'ID : " + updatedAccount.getId());
		}

	}

	public Account getAccountById(Long accountId) {
		try {
			return entityManager.find(Account.class, accountId);
		} catch (Exception e) {
			e.printStackTrace(); // Gérez les exceptions de manière appropriée
			return null;
		}
	}
	
	public List<Account> getAllAccounts() {
		
			return entityManager.createQuery("SELECT s FROM Account s", Account.class).getResultList();
		
	}

}
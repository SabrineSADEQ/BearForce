package fr.isika.cda.javaee.dao.accounts;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.utils.PasswordUtils;
import fr.isika.cda.javaee.utils.SessionUtils;
import fr.isika.cda.javaee.viewModel.AccountViewModel;


@Stateless
public class LoginDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Account findAccountByEmail(String email) {
		try {
			TypedQuery<Account> query = entityManager
					.createQuery("SELECT a FROM Account a WHERE a.email = :email", Account.class)
					.setParameter("email", email);

			return query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("Utilisateur non trouvé dans la base de données");
			return null; // Ou lancez une exception appropriée
		}
	}

	public void logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
	}

	public boolean validate(AccountViewModel accountVM) {
		Account accountBean = findAccountByEmail(accountVM.getEmail());
		return accountBean != null && PasswordUtils.checkPassword(accountVM.getPassword(), accountBean.getPassword());
	}

	public Account findAccountByEmailAndPassword(AccountViewModel accountVM, String email, String password) {
		Account accountBean = findAccountByEmail(email);
		if (accountBean != null && PasswordUtils.checkPassword(password, accountBean.getPassword())) {
			return accountBean;
		} else {
			throw new RuntimeException("Authentification échouée en raison de mots de passe incorrects");
		}
	}

}

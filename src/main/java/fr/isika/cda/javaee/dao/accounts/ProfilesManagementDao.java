package fr.isika.cda.javaee.dao.accounts;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.accounts.Role;

@Stateless
public class ProfilesManagementDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Profile getProfil(Long id) {
		return entityManager.find(Profile.class, id);
	}

	public List<Profile> getAllProfilesWithRoleCoach() {
		return getProfilesByRole(Role.COACH);
	}
	
	private List<Profile> getProfilesByRole(Role role) {
		return entityManager
				.createQuery("SELECT p FROM Profile p WHERE p.id in (SELECT acc.profile.id FROM Account acc WHERE acc.role = :roleParam)", Profile.class)
				.setParameter("roleParam", role)
				.getResultList();
	}

}

package fr.isika.cda.javaee.dao.gymspace;

	import javax.ejb.Stateless;
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.gymspace.Membership;

import java.util.List;
/**
 * 
 * @author Bitjoka Vincent
 *
 */

	@Stateless
	public class MembershipDao {

	    @PersistenceContext
	    private EntityManager entityManager;

	    public void saveMembership(Membership membership) {
	        entityManager.persist(membership);
	    }

	    public void updateMembership(Membership membership) {
	        entityManager.merge(membership);
	    }

	    public void deleteMembership(long membershipId) {
	        Membership membership = entityManager.find(Membership.class, membershipId);
	        if (membership != null) {
	            entityManager.remove(membership);
	        }
	    }

	    public Membership getMembershipById(long membershipId) {
	        return entityManager.find(Membership.class, membershipId);
	    }

	    public List<Membership> getAllMemberships() {
	        return entityManager.createQuery("SELECT m FROM Membership m", Membership.class).getResultList();
	    }
	    
	    // Ajout possible d'autres méthodes au besoin...
	}


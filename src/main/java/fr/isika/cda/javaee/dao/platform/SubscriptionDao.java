package fr.isika.cda.javaee.dao.platform;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.entity.platform.Subscription;
import java.util.List;

/**
 * 
 * @author Bitjoka Vincent
 *
 */

@Stateless
public class SubscriptionDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Subscription CreateSubscription(Subscription subscription) {
		entityManager.persist(subscription);
		return subscription;
	}
	public Subscription createSubscriptionAndUpdateRelations(Subscription subscription, Membership membership, Account account) {
		entityManager.persist(subscription);
		entityManager.merge(membership);
		entityManager.merge(account);
		return subscription;
	}

	public void updateSubscription(Subscription subscription) {
		entityManager.merge(subscription);
	}

	public void deleteSubscription(long subscriptionId) {
		Subscription subscription = entityManager.find(Subscription.class, subscriptionId);
		if (subscription != null) {
			entityManager.remove(subscription);
		}
	}

	public Subscription getSubscriptionById(long subscriptionId) {
		return entityManager.find(Subscription.class, subscriptionId);
	}

	public List<Subscription> getAllSubscriptions() {
		return entityManager.createQuery("SELECT s FROM Subscription s", Subscription.class).getResultList();
	}

}

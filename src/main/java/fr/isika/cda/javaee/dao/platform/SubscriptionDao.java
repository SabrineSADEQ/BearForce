package fr.isika.cda.javaee.dao.platform;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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


    
	private Subscription saveSubscription = new Subscription();
	
    
	public void CreateSubscription(Subscription subscription) {

        entityManager.persist(subscription);
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

	public void saveSubscription(Subscription subscription) {
		this.saveSubscription = subscription;
	}
    
   
}


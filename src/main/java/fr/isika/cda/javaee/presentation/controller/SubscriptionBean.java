package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.gymspace.MembershipDAO;
import fr.isika.cda.javaee.dao.platform.SubscriptionDAO;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.entity.platform.Subscription;

/**
 * 
 * @author Bitjoka Vincent
 *
 */

@Named
@ViewScoped
public class SubscriptionBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5563550480653977366L;
	
	@Inject
    private SubscriptionDAO subscriptionDAO; // Assume l'existence d'un DAO pour les abonnements
    
	@Inject
    private MembershipDAO membershipDAO; // Assume l'existence d'un DAO pour les adhésions

    private Subscription nouvelleSubscription;
    
    private long selectedMembershipId; // Pour stocker l'ID de l'adhésion choisi dans le formulaire

    @PostConstruct
    public void init() {
        nouvelleSubscription = new Subscription();
        createTestSubscription();
    }

    public void souscrireAbonnement() {
        try {
            Membership membershipChoisi = membershipDAO.getMembershipById(selectedMembershipId);
            nouvelleSubscription.setMembership(membershipChoisi);

            subscriptionDAO.saveSubscription(nouvelleSubscription);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Souscription réussie!", null));
            init(); // Réinitialiser les valeurs après la souscription
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la souscription.", null));
            e.printStackTrace();
        }
    }

    public List<Membership> getAllMemberships() {
    	return membershipDAO.getAllMemberships();
    }
    
    public void createTestSubscription() {
        // Création d'un abonnement
    	
//        Subscription subscription = new Subscription();
//        subscription.setStartDate(LocalDateTime.now());
//        subscription.setEndDate(LocalDateTime.now());
//        subscription.setAutoRenewal(true);
//        subscription.setDuration(1);

        Membership membership = new Membership("Silver", 50.0, 5, "Basic membership");

        // Enregistrement de l'adhésion
        membershipDAO.saveMembership(membership);

        // Attribution de l'adhésion à l'abonnement
//        subscription.setMembership(membership);

        // Enregistrement de l'abonnement
//        subscriptionDAO.saveSubscription(subscription);

        // Récupération de tous les abonnements
        List<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions();

        // Récupération de tous les adhésions
//        List<Membership> memberships = membershipDAO.getAllMemberships();
    }

    // Ajout possible d'autres méthodes...
    
    
    public Subscription getNouvelleSubscription() {
        return nouvelleSubscription;
    }
    public void setNouvelleSubscription(Subscription nouvelleSubscription) {
		this.nouvelleSubscription = nouvelleSubscription;
	}
    public long getSelectedMembershipId() {
        return selectedMembershipId;
    }

    public void setSelectedMembershipId(long selectedMembershipId) {
        this.selectedMembershipId = selectedMembershipId;
    }
}


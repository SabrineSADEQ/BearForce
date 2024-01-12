package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.gymspace.MembershipDao;
import fr.isika.cda.javaee.dao.platform.SubscriptionDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.entity.platform.Subscription;
import fr.isika.cda.javaee.presentation.viewmodel.SubscriptionViewModel;
import fr.isika.cda.javaee.utils.SessionUtils;

/**
 * Ce bean gère les opérations liées aux souscriptions de application Java EE.
 * Il utilise l'injection de dépendances pour accéder au DAO (SubscriptionDao),
 * interagit avecles beans (MembershipManagedBean et AccountManagedBean), et
 * propose des méthodes pour effectuer des opérations telles que l'inscription
 * et la récupération de la liste des souscriptions.
 * 
 *
 * @author Bitjoka Vincent
 *
 */
@Named
@ViewScoped
public class SubscriptionManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -324909137916793277L;

	private SubscriptionViewModel subscriptionViewModel = new SubscriptionViewModel();
	
	@Inject
	private SubscriptionDao subscriptionDao;
	
	@Inject
	private MembershipDao membershipDao;

	private Long selectedMembershipId;

	public List<Membership> getMemberships() {
		return membershipDao.getAllMemberships();
	}

	public String subscribe() {
		Membership membership = membershipDao.getMembershipByIdWithSubscriptions(selectedMembershipId);
		Account account = SessionUtils.getAccount();
		if (membership != null && account != null) {
			
			Subscription subscription = new Subscription();
			subscription.setAutoRenewal(subscriptionViewModel.isAutorenewal());
			// TODO : faire un petit calcul intelligent pour déduire la duration à partir de la end date et start date
			subscription.setDuration(subscriptionViewModel.getDuration());
			subscription.setStartDate(subscriptionViewModel.getStartDate().atStartOfDay());
			subscription.setEndDate(subscriptionViewModel.getEndDate().atStartOfDay());
			subscription.setMembership(membership);
			
			membership.getSubscriptions().add(subscription);

			account.setSubscription(subscription);
			
			subscription = subscriptionDao.createSubscriptionAndUpdateRelations(subscription, membership, account);
			
			// TODO : réactiver quand il y aura les objets pour le paiement (meme en simulation)
			return redirectToPaymentSubscription(subscription.getId());
			
//			return "ListeSubscriptions.xhtml";
		}
		
		System.err.println("Ceci ne doit pas arriver mais au cas où ça veut dire que account == null ou membership == null");
		return "";
	}
	
	public List<Subscription> subscriptionList() {
		// FIX : subs du user connecté et non pas toutes les subs
		return subscriptionDao.getAllSubscriptions();
	}
	
	public String redirectToPaymentSubscription(Long subscriptionId) {
		// url : pagePayment.xhtml?spaceId=xx&subscriptionId=yy
		return "pagePayment.xhtml?faces-redirect=true&amp;spaceId=" 
				+ SessionUtils.getAccount().getGymId()
				+ "&amp;subscriptionId=" + subscriptionId;
	}
	
	public void selectMemberShip(Long membershipId) {
		this.selectedMembershipId = membershipId;
	}
	
	public SubscriptionViewModel getSubscriptionViewModel() {
		return subscriptionViewModel;
	}

	public void setSubscriptionViewModel(SubscriptionViewModel subscriptionViewModel) {
		this.subscriptionViewModel = subscriptionViewModel;
	}
}

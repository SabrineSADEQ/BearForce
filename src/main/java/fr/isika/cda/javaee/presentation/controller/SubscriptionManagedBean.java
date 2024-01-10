package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.platform.SubscriptionDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.entity.platform.Pack;
import fr.isika.cda.javaee.entity.platform.Subscription;
import fr.isika.cda.javaee.presentation.viewmodel.EquipmentViewModel;
import fr.isika.cda.javaee.presentation.viewmodel.MembershipViewModel;
import fr.isika.cda.javaee.presentation.viewmodel.SubscriptionViewModel;

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
public class SubscriptionManagedBean implements Serializable {

	private LocalTime startDate;
	private LocalTime endDate;
	private boolean autoRenewal;
	private int duration;
	private SubscriptionViewModel subscriptionViewModel = new SubscriptionViewModel();
	private MembershipViewModel membershipViewModel = new MembershipViewModel();
	private MembershipManagedBean membershipManagedBean;
	private AccountManagedBean accountManagedBean;
	@Inject
	private SubscriptionDao subscriptionDao;

	public List<Subscription> subscriptionList() {
		return subscriptionDao.getAllSubscriptions();
	}

	public SubscriptionViewModel getSubscriptionViewModel() {
		return subscriptionViewModel;
	}

	public void setSubscriptionViewModel(SubscriptionViewModel subscriptionViewModel) {
		this.subscriptionViewModel = subscriptionViewModel;
	}

	public MembershipViewModel getMembershipViewModel() {
		return membershipViewModel;
	}

	public void setMembershipViewModel(MembershipViewModel membershipViewModel) {
		this.membershipViewModel = membershipViewModel;
	}

	public SubscriptionDao getSubscriptionDao() {
		return subscriptionDao;
	}

	public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
		this.subscriptionDao = subscriptionDao;
	}

	public MembershipManagedBean getMembershipManagedBean() {
		return membershipManagedBean;
	}

	public AccountManagedBean getAccountManagedBean() {
		return accountManagedBean;
	}

	public void subscribe() {
		Membership membership = membershipManagedBean.getSelectedMembership();

		Account account = accountManagedBean.getLoggedInAccount();

		if (membership != null && account != null) {
			Subscription subscription = new Subscription();
			subscription.setMembership(membership);
			account.setSubscription(subscription);

			SubscriptionDao subscriptionDao = new SubscriptionDao();
			subscriptionDao.saveSubscription(subscription);
		}
	}

	public LocalTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalTime startDate) {
		this.startDate = startDate;
	}

	public LocalTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalTime endDate) {
		this.endDate = endDate;
	}

	public boolean isAutoRenewal() {
		return autoRenewal;
	}

	public void setAutoRenewal(boolean autoRenewal) {
		this.autoRenewal = autoRenewal;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setMembershipManagedBean(MembershipManagedBean membershipManagedBean) {
		this.membershipManagedBean = membershipManagedBean;
	}

	public void setAccountManagedBean(AccountManagedBean accountManagedBean) {
		this.accountManagedBean = accountManagedBean;
	}

	public String paymentSubscription() {

		return "pagePayment.xhtml?faces-redirect=true";

	}

}

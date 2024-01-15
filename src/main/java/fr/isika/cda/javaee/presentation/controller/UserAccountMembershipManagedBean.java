package fr.isika.cda.javaee.presentation.controller;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.entity.platform.Subscription;
import fr.isika.cda.javaee.presentation.viewmodel.MembershipViewModel;
import fr.isika.cda.javaee.utils.SessionUtils;

@Named(value = "myMembershipBean")
@ViewScoped
public class UserAccountMembershipManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private boolean autorenewal;
	private int duration;
	
	private MembershipViewModel membershipViewModel = new MembershipViewModel();

	@PostConstruct
	private void init() {
		Account account = SessionUtils.getAccount();
		if(account != null && account.getSubscription() != null) {
			Membership membership = account.getSubscription().getMembership();
			membershipViewModel.setId(membership.getId());
			membershipViewModel.setName(membership.getName());
			membershipViewModel.setNbrOfActivities(membership.getNbrOfActivities());
			
			//TODO : les prix en int !!!
			membershipViewModel.setPrice(Double.valueOf(membership.getPrice()).intValue());
			membershipViewModel.setDescription(membership.getDescription());
			
			// TODO : vérifier si dans la liste il y a 
			// 1 - au moins 1 Subscription
			// 2 - laquelle prendre si plusieurs 
			Subscription subscription = membership.getSubscriptions().get(0);
			autorenewal = subscription.isAutoRenewal();
			startDate = subscription.getStartDate();
			endDate  = subscription.getEndDate();
			duration = (int) subscription.getDuration();
		}
	}
	
	public boolean hasSubscription() {
		Account account = SessionUtils.getAccount();
		return (account != null && account.getSubscription() != null);
	}
	
	public MembershipViewModel getMembershipViewModel() {
		return membershipViewModel;
	}

	public void setMembershipViewModel(MembershipViewModel membershipViewModel) {
		this.membershipViewModel = membershipViewModel;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public boolean isAutorenewal() {
		return autorenewal;
	}

	public void setAutorenewal(boolean autorenewal) {
		this.autorenewal = autorenewal;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	
}

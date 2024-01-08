package fr.isika.cda.javaee.presentation.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.platform.SubscriptionDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.entity.platform.Subscription;
import java.io.Serializable;
import java.time.LocalTime;

@ManagedBean
@SessionScoped
public class SubscriptionManagedBean implements Serializable {

    @Inject
    private SubscriptionDao subscriptionDAO;

    @ManagedProperty(value = "#{membershipManagedBean}")
    private MembershipManagedBean membershipManagedBean;

    @ManagedProperty(value = "#{accountManagedBean}")
    private AccountManagedBean accountManagedBean;

    private LocalTime startDate;
    private LocalTime endDate;
    private boolean autoRenewal;
    private int duration;

    public void subscribe() {
    	System.out.println("msg");
        Membership membership = membershipManagedBean.getSelectedMembership();
        Account account = accountManagedBean.getLoggedInAccount();

        if (membership != null && account != null) {
            Subscription subscription = new Subscription();
            subscription.setMembership(membership);

            account.setSubscription(subscription);
//            subscription.setAccount(account);

          SubscriptionDao subscriptionDao2 = new SubscriptionDao();
          subscriptionDao2.CreateSubscription(subscription);
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
}


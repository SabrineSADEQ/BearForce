package fr.isika.cda.javaee.presentation.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.gymspace.MembershipDao;
import fr.isika.cda.javaee.entity.gymspace.Membership;

import java.io.Serializable;
import java.util.List;
@Named
@ViewScoped
public class MembershipManagedBean implements Serializable {

    @Inject
    private MembershipDao membershipDao;

    private List<Membership> memberships;
    private Membership selectedMembership;

    @PostConstruct
    public void init() {
      
    }

    public List<Membership> getMemberships() {
    	  memberships = membershipDao.getAllMemberships();
        return memberships;
    }

    public Membership getSelectedMembership() {
        return selectedMembership;
    }

    public void setSelectedMembership(Membership selectedMembership) {
        this.selectedMembership = selectedMembership;
    }
}


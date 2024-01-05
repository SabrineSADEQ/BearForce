package fr.isika.cda.javaee.presentation.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.gymspace.MembershipDAO;
import fr.isika.cda.javaee.entity.gymspace.Membership;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class MembershipManagedBean implements Serializable {

    @Inject
    private MembershipDAO membershipDAO;

    private List<Membership> memberships;
    private Membership selectedMembership;

    public void init() {
        memberships = membershipDAO.getAllMemberships();
    }

   

    public List<Membership> getMemberships() {
        return memberships;
    }

    public Membership getSelectedMembership() {
        return selectedMembership;
    }

    public void setSelectedMembership(Membership selectedMembership) {
        this.selectedMembership = selectedMembership;
    }
}


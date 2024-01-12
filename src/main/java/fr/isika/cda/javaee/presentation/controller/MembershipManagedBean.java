package fr.isika.cda.javaee.presentation.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.isika.cda.javaee.dao.gymspace.MembershipDao;
import fr.isika.cda.javaee.entity.gymspace.Membership;
import fr.isika.cda.javaee.presentation.viewmodel.MembershipViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe gère la récupération des abonnements depuis la base de données,
 * permet à l'utilisateur de sélectionner un abonnement, et fournit une méthode
 * pour créer de nouveaux abonnements en utilisant un modèle de vue
 * 
 * @author Bitjoka Vincent
 *
 */
@Named
@ViewScoped
public class MembershipManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MembershipDao membershipDao;

	private List<Membership> memberships = new ArrayList<Membership>();
	private Membership selectedMembership = new Membership();
	private MembershipViewModel membershipViewModel = new MembershipViewModel();

	public MembershipDao getMembershipDao() {
		return membershipDao;
	}

	public void setMembershipDao(MembershipDao membershipDao) {
		this.membershipDao = membershipDao;
	}

	public MembershipViewModel getMembershipViewModel() {
		return membershipViewModel;
	}

	public void setMembershipViewModel(MembershipViewModel membershipViewModel) {
		this.membershipViewModel = membershipViewModel;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	@PostConstruct
	public void init() {
		this.memberships = getMemberships();
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

	public void createMembership() {
		Membership newMembership = new Membership();
		newMembership.setName(membershipViewModel.getName());
		newMembership.setNbrOfActivities(membershipViewModel.getNbrOfActivities());
		newMembership.setPrice(membershipViewModel.getPrice());

		membershipDao.addMembership(newMembership);

		// Réinitialise le viewModel après la création
		membershipViewModel = new MembershipViewModel();
	}

}

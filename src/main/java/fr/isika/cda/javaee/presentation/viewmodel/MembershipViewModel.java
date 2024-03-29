package fr.isika.cda.javaee.presentation.viewmodel;

import java.util.ArrayList;
import java.util.List;

import fr.isika.cda.javaee.entity.platform.Subscription;

public class MembershipViewModel {
	private Long id;

	private String name;
	private int price;
	private int nbrOfActivities;
	private String description;

	private List<Subscription> subscriptionsList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Subscription> getSubscriptionsList() {
		return subscriptionsList;
	}

	public void setSubscriptionsList(List<Subscription> subscriptionsList) {
		this.subscriptionsList = subscriptionsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNbrOfActivities() {
		return nbrOfActivities;
	}

	public void setNbrOfActivities(int nbrOfActivities) {
		this.nbrOfActivities = nbrOfActivities;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

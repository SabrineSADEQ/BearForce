package fr.isika.cda.javaee.entity.gymspace.business;

public enum ActivityCategory {
	CARDIO ("Cardio"), 
	FITNESS ("Fitness"), 
	FORCE ("Force musculaire"), 
	NATATION ("Natation"), 
	COMBAT ("Combat"),
	ART_MARTIAL ("Art martial"),
	DANCE ("Danse"),
	RELAXATION ("Relaxation");
	
	private String libelle;
	
	private ActivityCategory(String libelle) {
		this.libelle = libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
}
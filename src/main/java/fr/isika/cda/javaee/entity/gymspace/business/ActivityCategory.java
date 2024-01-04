package fr.isika.cda.javaee.entity.gymspace.business;

public enum ActivityCategory {
	CARDIO ("Cardio"), 
	FITNESS ("Fitness"), 
	FORCE ("Force musculaire"), 
	PISCINE ("Natation"), 
	DETENTE ("Détante"), 
	RELAXATION ("Relax");
	
	private String libelle;
	
	private ActivityCategory(String libelle) {
		this.libelle = libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
}
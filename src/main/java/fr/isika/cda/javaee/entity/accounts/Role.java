package fr.isika.cda.javaee.entity.accounts;

public enum Role {
	
	SUPER_ADMIN ("Super Administrateur"),
	GESTIONNAIRE ("Gestionnaire de salle de sport"),
	ADHERENT ("Adhérent"),
	VISITEUR ("Visiteur")
	;
	
	private String libelle;
	
	private Role(String libelle) {
		this.libelle = libelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
}

package org.formation.spring.core.persistence.model.enumeration;


public enum TypeVoie {

	RUE("Rue"), AVENUE("Avenue"), BOULEVARD("Boulevard"), IMPASSE("Impasse");

	private String libelle;

	private TypeVoie(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

}

package org.formation.spring.core.persistence.model;


import java.util.Date;

import org.formation.spring.core.persistence.model.enumeration.FormeJuridique;

public class Entreprise {

	private int id;

	private String denomination;

	private String siren;

	private Adresse adresse;

	private String telephone;

	private FormeJuridique formeJuridique;

	private Date dateCreation;

	private Secteur secteur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getSiren() {
		return siren;
	}

	public void setSiren(String siren) {
		this.siren = siren;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public FormeJuridique getFormeJuridique() {
		return formeJuridique;
	}

	public void setFormeJuridique(FormeJuridique formeJuridique) {
		this.formeJuridique = formeJuridique;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

}

package org.formation.spring.core.persistence.database;

import java.util.ArrayList;
import java.util.List;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

public class CacheDatabase {

	private List<Entreprise> entreprises = new ArrayList<>();

	private List<Adresse> adresses = new ArrayList<>();

	private List<Secteur> secteurs = new ArrayList<>();

	public List<Entreprise> getEntreprises() {
		return entreprises;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

}
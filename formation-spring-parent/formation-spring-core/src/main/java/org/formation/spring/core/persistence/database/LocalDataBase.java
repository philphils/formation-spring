package org.formation.spring.core.persistence.database;

import java.util.ArrayList;
import java.util.List;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;

public class LocalDataBase {

	public static final LocalDataBase access = new LocalDataBase();

	private LocalDataBase() {}

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

	// TODO Faire les consignes
	// Compléter les tests
	// Revoir la forme du model generator
	// Revoir l'EntrepriseServiceImpl
	// Etape locator
	// Remplacer le locator par spring avec annotations
	// TODO Faire la correction
	// TODO Prévoir un cas de Configuration et @Bean au niveau méthode pour la database ?
	// TODO Prévoir une histoire de profile (optionnel) --> deux fonctionnement des daos
}
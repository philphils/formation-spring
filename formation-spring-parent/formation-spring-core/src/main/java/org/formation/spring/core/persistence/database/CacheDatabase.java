package org.formation.spring.core.persistence.database;

import java.util.ArrayList;
import java.util.List;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class CacheDatabase {

	private RandomModelGenerator randomModelGenerator;

	private List<Entreprise> entreprises = new ArrayList<>();

	private List<Adresse> adresses = new ArrayList<>();

	private List<Secteur> secteurs = new ArrayList<>();

	public CacheDatabase(RandomModelGenerator randomModelGenerator) {
		super();
		this.randomModelGenerator = randomModelGenerator;
	}

	public List<Entreprise> getEntreprises() {
		return entreprises;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	@PostConstruct
	public void setUp() {

		secteurs.add(randomModelGenerator.generateSecteur());
		secteurs.add(randomModelGenerator.generateSecteur());
		secteurs.add(randomModelGenerator.generateSecteur());

	}

	@PreDestroy
	public void destroy() {

		System.out.println("Libération des ressources avant destruction du bean");

		entreprises.clear();
		secteurs.clear();
		adresses.clear();
	}

}
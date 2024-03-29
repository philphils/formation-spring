package org.formation.spring.core.service;

import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;

import com.github.javafaker.Faker;

public interface EntrepriseService {

	/**
	 * Créer une {@link Entreprise}. Si aucun {@link Secteur} n'existe alors un
	 * {@link Secteur} est créé. Sinon on la rattache à un {@link Secteur} existant.
	 * Les champs sont rempli aléatoiremeent avec {@link Faker}
	 * 
	 * @return
	 */
	public Entreprise createRandomEntreprise();
}

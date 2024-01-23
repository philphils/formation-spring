package org.formation.spring.core.persistence.model.generator;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;

public interface ModelGenerator {

	public Entreprise generateEntreprise();

	public Adresse generateAdresse();

	public Secteur generateSecteur();
}

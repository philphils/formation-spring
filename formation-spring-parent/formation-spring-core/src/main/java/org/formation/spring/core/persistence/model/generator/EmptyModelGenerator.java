package org.formation.spring.core.persistence.model.generator;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.springframework.stereotype.Component;

// On a la possibilité de nommer notre bean pour y faire référence via Qualifier par exemple
// Le nom par défaut du bean est le nom de la classe commençant par une minuscule. Ici : "emptyModelGenerator"
@Component(value = "arthur")
public class EmptyModelGenerator implements ModelGenerator {

	@Override
	public Entreprise generateEntreprise() {
		return new Entreprise();
	}

	@Override
	public Adresse generateAdresse() {
		return new Adresse();
	}

	@Override
	public Secteur generateSecteur() {
		return new Secteur();
	}

}

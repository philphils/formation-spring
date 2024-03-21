package org.formation.spring.core.persistence.model.generator;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("emptyModel")
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

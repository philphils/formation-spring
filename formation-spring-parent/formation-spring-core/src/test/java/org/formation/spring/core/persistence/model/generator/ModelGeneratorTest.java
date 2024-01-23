package org.formation.spring.core.persistence.model.generator;

import static org.assertj.core.api.Assertions.assertThat;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.junit.Test;

public class ModelGeneratorTest {

	ModelGenerator generator = new ModelGenerator();

	@Test
	public void generateEntreprise() {
		// GIVEN

		// WHEN
		Entreprise entreprise = generator.generateEntreprise();

		// THEN
		assertThat(entreprise).hasNoNullFieldsOrPropertiesExcept("secteur", "adresse");
	}

	@Test
	public void generateAdresse() {
		// GIVEN

		// WHEN
		Adresse adresse = generator.generateAdresse();

		// THEN
		assertThat(adresse).hasNoNullFieldsOrProperties();
	}

	@Test
	public void generateSecteur() {
		// GIVEN

		// WHEN
		Secteur secteur = generator.generateSecteur();

		// THEN
		assertThat(secteur).hasNoNullFieldsOrProperties();
	}
}


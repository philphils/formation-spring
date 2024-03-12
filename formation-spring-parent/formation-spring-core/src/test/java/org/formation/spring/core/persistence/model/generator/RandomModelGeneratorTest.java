package org.formation.spring.core.persistence.model.generator;

import static org.assertj.core.api.Assertions.assertThat;

import org.formation.spring.core.conf.GenerationConfiguration;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// Pour lancer un test d'intégration spring avec JUnit4, pour jupiter, utiliser @ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
// On doit déclarer le contexte spring du test, ici on se réfère uniquement à la classe de configuration de generation
@ContextConfiguration(classes = GenerationConfiguration.class)
public class RandomModelGeneratorTest {

	@Autowired
	private RandomModelGenerator generator;

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


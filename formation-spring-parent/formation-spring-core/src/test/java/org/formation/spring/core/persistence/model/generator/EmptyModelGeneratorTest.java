package org.formation.spring.core.persistence.model.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCollection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.formation.spring.core.CoreApplication;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CoreApplication.class)
public class EmptyModelGeneratorTest {

	@Autowired
	@Qualifier("emptyModel")
	private ModelGenerator modelGenerator;

	@Autowired
	private List<ModelGenerator> generators;

	@Test
	public void testEmptyModelGenerator() {

		Entreprise entreprise = modelGenerator.generateEntreprise();

		assertNull(entreprise.getAdresse());
		assertNull(entreprise.getDenomination());
		assertNull(entreprise.getFormeJuridique());

		Secteur secteur = modelGenerator.generateSecteur();

		assertNull(secteur.getLibelleNomenclature());
		assertNull(secteur.getCodeNaf());

		Adresse adresse = modelGenerator.generateAdresse();

		assertNull(adresse.getNomVoie());
		assertNull(adresse.getNumero());
		assertNull(adresse.getPays());

	}

	@Test
	public void testListModelGenerator() {

		assertEquals(2, generators.size());

		assertThatCollection(generators).anySatisfy(model -> assertThat(model).isInstanceOf(EmptyModelGenerator.class));
		assertThatCollection(generators)
				.anySatisfy(model -> assertThat(model).isInstanceOf(RandomModelGenerator.class));

	}

}

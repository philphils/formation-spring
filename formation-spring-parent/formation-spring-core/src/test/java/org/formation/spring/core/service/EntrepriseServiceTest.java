package org.formation.spring.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.formation.spring.core.persistence.dao.AdresseDaoImpl;
import org.formation.spring.core.persistence.dao.ModelDao;
import org.formation.spring.core.persistence.database.LocalDataBase;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.generator.ModelGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class EntrepriseServiceTest {

	private ModelDao<Adresse> mockedAdresseDao;

	private EntrepriseService service;

	@Before
	public void setUp() {
		this.mockedAdresseDao = Mockito.mock(AdresseDaoImpl.class);
		this.service = new EntrepriseServiceImpl(mockedAdresseDao);
	}

	@After
	public void clearDatabase() {
		LocalDataBase.access.getAdresses().clear();
		LocalDataBase.access.getEntreprises().clear();
		LocalDataBase.access.getSecteurs().clear();
	}

	@Test
	public void createRandomEntrepriseWhenSecteurNotExists() {
		// GIVEN

		// WHEN
		Entreprise entreprise = service.createRandomEntreprise();

		// THEN
		assertThat(entreprise).hasNoNullFieldsOrProperties();
		verify(mockedAdresseDao, times(1)).create(any(Adresse.class));
		// Il n'y a pas d'injection de dépendance pour EntrepriseDao, il est crée à la volée dans la méthode
		// On ne peut donc pas mocker EntrepriseDao
		// FIXME On ne peut pas vérifier qu'une entreprise est crée en base
		// Il n'y a pas d'injection de dépendance pour SecteurDao, il est instancié dans le constructeur
		// On ne peut donc pas mocker SecteurDao
		// FIXME On ne peut pas vérifier qu'un secteur a été crée en base

	}

	@Test
	public void createRandomEntrepriseWhenSecteurExists() {
		// GIVEN
		LocalDataBase.access.getSecteurs().add(new ModelGenerator().generateSecteur());

		// WHEN
		Entreprise entreprise = service.createRandomEntreprise();

		// THEN
		assertThat(entreprise).hasNoNullFieldsOrProperties();
		verify(mockedAdresseDao, times(1)).create(any(Adresse.class));
		// FIXME On ne peut pas vérifier qu'une entreprise est crée en base
		// FIXME On ne peut pas vérifier qu'un secteur n'a pas été crée en base

	}
}

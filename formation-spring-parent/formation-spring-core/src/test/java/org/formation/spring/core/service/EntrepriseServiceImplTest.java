package org.formation.spring.core.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.formation.spring.core.persistence.dao.ModelDao;
import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EntrepriseServiceImplTest {

	@Mock
	private ModelDao<Adresse> mockedAdresseDao;

	private EntrepriseService service;

	@Before
	public void setUp() {
		this.service = new EntrepriseServiceImpl(mockedAdresseDao);
	}

	@After
	public void clearDatabase() {
		CacheDatabase.access.getAdresses().clear();
		CacheDatabase.access.getEntreprises().clear();
		CacheDatabase.access.getSecteurs().clear();
	}

	@Test
	public void createRandomEntrepriseWhenSecteurNotExists() {
		// GIVEN

		// WHEN
		Entreprise entreprise = service.createRandomEntreprise();

		// THEN
		verify(mockedAdresseDao).create(any(Adresse.class));
		// org.mockito.Mockito.verify(...) permet de tester qu'une méthode d'un mock a
		// bien été utilisée.
		// Pour tester au contraire qu'elle n'a pas été utilisée ou utilisée plusieurs
		// fois,
		// on peut utiliser verify(mockedAdresseDao, times(nbUtilisaiton))

		// Il n'y a pas d'injection de dépendance pour EntrepriseDao et pour SecteurDao
		// Mettre en place l'injection de dépendance au niveau des classes métier
		// Puis injecter les Mocks dans la méthode setUp pour pouvoir :
		// FIXME Vérifier qu'une entreprise est crée en base
		// FIXME Vérifier qu'un secteur a été crée en base

	}

	@Test
	public void createRandomEntrepriseWhenSecteurExists() {
		// GIVEN
		CacheDatabase.access.getSecteurs().add(new RandomModelGenerator().generateSecteur());

		// WHEN
		Entreprise entreprise = service.createRandomEntreprise();

		// THEN
		verify(mockedAdresseDao).create(any(Adresse.class));
		// FIXME Vérifier qu'une entreprise est crée en base
		// FIXME Vérifier qu'un secteur n'a pas été crée en base

	}
}

package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.CoreApplication;
import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//Pour lancer un test d'intégration spring avec JUnit4, pour jupiter, utiliser @ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
//On doit déclarer le contexte spring du test, ici on se réfère à la configuration applicative entière
@ContextConfiguration(classes = CoreApplication.class)
public class SecteurCacheDatabaseDaoImplTest {

	@Autowired
	private RandomModelGenerator generator;

	@Autowired
	private SecteurCacheDatabaseDaoImpl dao;

	@Autowired
	private CacheDatabase cacheDatabase;

	@After
	public void afterEachTest() {
		cacheDatabase.getSecteurs().clear();
	}

	@Test
	public void getSecteurById() {
		// GIVEN
		Secteur secteur = generator.generateSecteur();
		cacheDatabase.getSecteurs().add(secteur);

		// WHEN
		Secteur returnedSecteur = dao.getById(secteur.getId());

		// THEN
		assertEquals(secteur, returnedSecteur);
	}

	@Test
	public void getAll() {
		// GIVEN
		Secteur secteur1 = generator.generateSecteur();
		Secteur secteur2 = generator.generateSecteur();
		cacheDatabase.getSecteurs().add(secteur1);
		cacheDatabase.getSecteurs().add(secteur2);

		// WHEN
		List<Secteur> returnedSecteurs = dao.getAll();

		// THEN
		assertEquals(2, returnedSecteurs.size());
	}

	@Test
	public void deleteSecteur() {
		// GIVEN
		Secteur secteur = generator.generateSecteur();
		cacheDatabase.getSecteurs().add(secteur);

		// WHEN
		dao.delete(secteur);

		// THEN
		assertEquals(0, cacheDatabase.getSecteurs().size());
	}

	@Test
	public void createSecteur() {
		// GIVEN
		Secteur secteur = generator.generateSecteur();

		// WHEN
		dao.create(secteur);

		// THEN
		assertEquals(1, cacheDatabase.getSecteurs().size());
	}

	@Test
	public void updateSecteur() {
		// GIVEN
		Secteur secteur = generator.generateSecteur();
		cacheDatabase.getSecteurs().add(secteur);

		// WHEN
		secteur.setCodeNaf("-1");
		Secteur updatedSecteur = dao.update(secteur);

		// THEN
		assertEquals(secteur, updatedSecteur);
		assertEquals("-1", updatedSecteur.getCodeNaf());
	}

}

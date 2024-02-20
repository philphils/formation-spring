package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Secteur;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SecteurCacheDatabaseDaoImplTest {

	private SecteurCacheDatabaseDaoImpl dao;

	private CacheDatabase database;

	@After
	public void afterEachTest() {
		database.getSecteurs().clear();
	}

	@Test
	public void getSecteurById() {
		// GIVEN
		Secteur secteur = new Secteur();
		secteur.setId(1);
		database.getSecteurs().add(secteur);

		// WHEN
		Secteur returnedSecteur = dao.getById(1);

		// THEN
		assertEquals(secteur, returnedSecteur);
	}

	@Test
	public void getAll() {
		// GIVEN
		Secteur secteur1 = new Secteur();
		Secteur secteur2 = new Secteur();
		database.getSecteurs().add(secteur1);
		database.getSecteurs().add(secteur2);

		// WHEN
		List<Secteur> returnedSecteurs = dao.getAll();

		// THEN
		assertEquals(2, returnedSecteurs.size());
	}

	@Test
	public void deleteSecteur() {
		// GIVEN
		Secteur secteur = new Secteur();
		database.getSecteurs().add(secteur);

		// WHEN
		dao.delete(secteur);

		// THEN
		assertEquals(0, database.getSecteurs().size());
	}

	@Test
	public void createSecteur() {
		// GIVEN
		Secteur secteur = new Secteur();

		// WHEN
		dao.create(secteur);

		// THEN
		assertEquals(1, database.getSecteurs().size());
	}

	@Test
	public void updateSecteur() {
		// GIVEN
		Secteur secteur = new Secteur();
		database.getSecteurs().add(secteur);

		// WHEN
		secteur.setCodeNaf("-1");
		Secteur updatedSecteur = dao.update(secteur);

		// THEN
		assertEquals(secteur, updatedSecteur);
		assertEquals("-1", updatedSecteur.getCodeNaf());
	}

}

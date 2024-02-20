package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.persistence.dao.SecteurCacheDatabaseDaoImpl;
import org.formation.spring.core.persistence.database.CacheDataBase;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.junit.After;
import org.junit.Test;

public class SecteurCacheDatabaseDaoImplTest {

	private RandomModelGenerator generator = new RandomModelGenerator();

	private SecteurCacheDatabaseDaoImpl dao = new SecteurCacheDatabaseDaoImpl();

	@After
	public void afterEachTest() {
		CacheDataBase.access.getSecteurs().clear();
	}

	@Test
	public void getSecteurById() {
		// GIVEN
		Secteur secteur = generator.generateSecteur();
		CacheDataBase.access.getSecteurs().add(secteur);

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
		CacheDataBase.access.getSecteurs().add(secteur1);
		CacheDataBase.access.getSecteurs().add(secteur2);

		// WHEN
		List<Secteur> returnedSecteurs = dao.getAll();

		// THEN
		assertEquals(2, returnedSecteurs.size());
	}

	@Test
	public void deleteSecteur() {
		// GIVEN
		Secteur secteur = generator.generateSecteur();
		CacheDataBase.access.getSecteurs().add(secteur);

		// WHEN
		dao.delete(secteur);

		// THEN
		assertEquals(0, CacheDataBase.access.getSecteurs().size());
	}

	@Test
	public void createSecteur() {
		// GIVEN
		Secteur secteur = generator.generateSecteur();

		// WHEN
		dao.create(secteur);

		// THEN
		assertEquals(1, CacheDataBase.access.getSecteurs().size());
	}

	@Test
	public void updateSecteur() {
		// GIVEN
		Secteur secteur = generator.generateSecteur();
		CacheDataBase.access.getSecteurs().add(secteur);

		// WHEN
		secteur.setCodeNaf("-1");
		Secteur updatedSecteur = dao.update(secteur);

		// THEN
		assertEquals(secteur, updatedSecteur);
		assertEquals("-1", updatedSecteur.getCodeNaf());
	}

}

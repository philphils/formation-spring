package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDataBase;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.junit.After;
import org.junit.Test;

public class AdresseCacheDatabaseDaoImplTest {

	private RandomModelGenerator generator = new RandomModelGenerator();

	private AdresseCacheDatabaseDaoImpl dao = new AdresseCacheDatabaseDaoImpl();

	@After
	public void afterEachTest() {
		CacheDataBase.access.getAdresses().clear();
	}

	@Test
	public void getAdresseById() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		CacheDataBase.access.getAdresses().add(adresse);

		// WHEN
		Adresse returnedAdresse = dao.getById(adresse.getId());

		// THEN
		assertEquals(adresse, returnedAdresse);
	}

	@Test
	public void getAll() {
		// GIVEN
		Adresse adresse1 = generator.generateAdresse();
		Adresse adresse2 = generator.generateAdresse();
		CacheDataBase.access.getAdresses().add(adresse1);
		CacheDataBase.access.getAdresses().add(adresse2);

		// WHEN
		List<Adresse> returnedAdresses = dao.getAll();

		// THEN
		assertEquals(2, returnedAdresses.size());
	}

	@Test
	public void deleteAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		CacheDataBase.access.getAdresses().add(adresse);

		// WHEN
		dao.delete(adresse);

		// THEN
		assertEquals(0, CacheDataBase.access.getAdresses().size());
	}

	@Test
	public void createAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();

		// WHEN
		dao.create(adresse);

		// THEN
		assertEquals(1, CacheDataBase.access.getAdresses().size());
	}

	@Test
	public void updateAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		CacheDataBase.access.getAdresses().add(adresse);

		// WHEN
		adresse.setNumero("-1");
		Adresse updatedAdresse = dao.update(adresse);

		// THEN
		assertEquals(adresse, updatedAdresse);
		assertEquals("-1", updatedAdresse.getNumero());
	}

}

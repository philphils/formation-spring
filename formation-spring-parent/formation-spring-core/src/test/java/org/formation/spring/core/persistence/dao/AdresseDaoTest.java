package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.persistence.database.LocalDataBase;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.generator.ModelGenerator;
import org.junit.After;
import org.junit.Test;

public class AdresseDaoTest {

	private ModelGenerator generator = new ModelGenerator();

	private AdresseDaoImpl dao = new AdresseDaoImpl();

	@After
	public void afterEachTest() {
		LocalDataBase.access.getAdresses().clear();
	}

	@Test
	public void getAdresseById() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		LocalDataBase.access.getAdresses().add(adresse);

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
		LocalDataBase.access.getAdresses().add(adresse1);
		LocalDataBase.access.getAdresses().add(adresse2);

		// WHEN
		List<Adresse> returnedAdresses = dao.getAll();

		// THEN
		assertEquals(2, returnedAdresses.size());
	}

	@Test
	public void deleteAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		LocalDataBase.access.getAdresses().add(adresse);

		// WHEN
		dao.delete(adresse);

		// THEN
		assertEquals(0, LocalDataBase.access.getAdresses().size());
	}

	@Test
	public void createAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();

		// WHEN
		dao.create(adresse);

		// THEN
		assertEquals(1, LocalDataBase.access.getAdresses().size());
	}

	@Test
	public void updateAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		LocalDataBase.access.getAdresses().add(adresse);

		// WHEN
		adresse.setNumero("-1");
		Adresse updatedAdresse = dao.update(adresse);

		// THEN
		assertEquals(adresse, updatedAdresse);
		assertEquals("-1", updatedAdresse.getNumero());
	}

}

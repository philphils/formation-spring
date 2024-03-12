package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.CoreApplication;
import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Adresse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// Pour lancer un test d'intégration spring avec JUnit4, pour jupiter, utiliser @ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
// On doit déclarer le contexte spring du test, ici on se réfère à la configuration applicative entière
@ContextConfiguration(classes = CoreApplication.class)
public class AdresseCacheDatabaseDaoImplTest {

	@Autowired
	private AdresseCacheDatabaseDaoImpl dao;

	@Autowired
	private CacheDatabase database;

	@After
	public void afterEachTest() {
		database.getAdresses().clear();
	}

	@Test
	public void getAdresseById() {
		// GIVEN
		Adresse adresse = new Adresse();
		adresse.setId(1);
		database.getAdresses().add(adresse);

		// WHEN
		Adresse returnedAdresse = dao.getById(1);

		// THEN
		assertEquals(adresse, returnedAdresse);
	}

	@Test
	public void getAll() {
		// GIVEN
		Adresse adresse1 = new Adresse();
		Adresse adresse2 = new Adresse();
		database.getAdresses().add(adresse1);
		database.getAdresses().add(adresse2);

		// WHEN
		List<Adresse> returnedAdresses = dao.getAll();

		// THEN
		assertEquals(2, returnedAdresses.size());
	}

	@Test
	public void deleteAdresse() {
		// GIVEN
		Adresse adresse = new Adresse();
		database.getAdresses().add(adresse);

		// WHEN
		dao.delete(adresse);

		// THEN
		assertEquals(0, database.getAdresses().size());
	}

	@Test
	public void createAdresse() {
		// GIVEN
		Adresse adresse = new Adresse();

		// WHEN
		dao.create(adresse);

		// THEN
		assertEquals(1, database.getAdresses().size());
	}

	@Test
	public void updateAdresse() {
		// GIVEN
		Adresse adresse = new Adresse();
		database.getAdresses().add(adresse);

		// WHEN
		adresse.setNumero("-1");
		Adresse updatedAdresse = dao.update(adresse);

		// THEN
		assertEquals(adresse, updatedAdresse);
		assertEquals("-1", updatedAdresse.getNumero());
	}

}

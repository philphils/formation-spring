package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.CoreApplication;
import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Adresse;
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
public class AdresseCacheDatabaseDaoImplTest {

	@Autowired
	private RandomModelGenerator generator;

	@Autowired
	private AdresseCacheDatabaseDaoImpl dao;

	@Autowired
	private CacheDatabase cacheDatabase;

	@After
	public void afterEachTest() {
		cacheDatabase.getAdresses().clear();
	}

	@Test
	public void getAdresseById() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		cacheDatabase.getAdresses().add(adresse);

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
		cacheDatabase.getAdresses().add(adresse1);
		cacheDatabase.getAdresses().add(adresse2);

		// WHEN
		List<Adresse> returnedAdresses = dao.getAll();

		// THEN
		assertEquals(2, returnedAdresses.size());
	}

	@Test
	public void deleteAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		cacheDatabase.getAdresses().add(adresse);

		// WHEN
		dao.delete(adresse);

		// THEN
		assertEquals(0, cacheDatabase.getAdresses().size());
	}

	@Test
	public void createAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();

		// WHEN
		dao.create(adresse);

		// THEN
		assertEquals(1, cacheDatabase.getAdresses().size());
	}

	@Test
	public void updateAdresse() {
		// GIVEN
		Adresse adresse = generator.generateAdresse();
		cacheDatabase.getAdresses().add(adresse);

		// WHEN
		adresse.setNumero("-1");
		Adresse updatedAdresse = dao.update(adresse);

		// THEN
		assertEquals(adresse, updatedAdresse);
		assertEquals("-1", updatedAdresse.getNumero());
	}

}

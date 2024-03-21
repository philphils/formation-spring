package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.CoreApplication;
import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Entreprise;
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
public class EntrepriseCacheDatabaseDaoImplTest {

	@Autowired
	private RandomModelGenerator generator;

	@Autowired
	private EntrepriseCacheDatabaseDaoImpl dao;

	@Autowired
	private CacheDatabase cacheDatabase;

	@After
	public void afterEachTest() {
		cacheDatabase.getEntreprises().clear();
	}

	@Test
	public void getEntrepriseById() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();
		cacheDatabase.getEntreprises().add(entreprise);

		// WHEN
		Entreprise returnedEntreprise = dao.getById(entreprise.getId());

		// THEN
		assertEquals(entreprise, returnedEntreprise);
	}

	@Test
	public void getEntrepriseBySiren() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();
		cacheDatabase.getEntreprises().add(entreprise);

		// WHEN
		Entreprise returnedEntreprise = dao.getBySiren(entreprise.getSiren());

		// THEN
		assertEquals(entreprise, returnedEntreprise);
	}

	@Test
	public void getAll() {
		// GIVEN
		Entreprise entreprise1 = generator.generateEntreprise();
		Entreprise entreprise2 = generator.generateEntreprise();
		cacheDatabase.getEntreprises().add(entreprise1);
		cacheDatabase.getEntreprises().add(entreprise2);

		// WHEN
		List<Entreprise> returnedEntreprises = dao.getAll();

		// THEN
		assertEquals(2, returnedEntreprises.size());
	}

	@Test
	public void deleteEntreprise() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();
		cacheDatabase.getEntreprises().add(entreprise);

		// WHEN
		dao.delete(entreprise);

		// THEN
		assertEquals(0, cacheDatabase.getEntreprises().size());
	}

	@Test
	public void createEntreprise() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();

		// WHEN
		dao.create(entreprise);

		// THEN
		assertEquals(1, cacheDatabase.getEntreprises().size());
	}

	@Test
	public void updateEntreprise() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();
		cacheDatabase.getEntreprises().add(entreprise);

		// WHEN
		entreprise.setSiren("-1");
		Entreprise updatedEntreprise = dao.update(entreprise);

		// THEN
		assertEquals(entreprise, updatedEntreprise);
		assertEquals("-1", updatedEntreprise.getSiren());
	}

}

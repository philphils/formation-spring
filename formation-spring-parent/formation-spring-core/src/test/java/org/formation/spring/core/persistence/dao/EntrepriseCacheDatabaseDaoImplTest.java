package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Entreprise;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class EntrepriseCacheDatabaseDaoImplTest {

	private EntrepriseCacheDatabaseDaoImpl dao;

	private CacheDatabase database;

	@After
	public void afterEachTest() {
		database.getEntreprises().clear();
	}

	@Test
	public void getEntrepriseById() {
		// GIVEN
		Entreprise entreprise = new Entreprise();
		entreprise.setId(1);
		database.getEntreprises().add(entreprise);

		// WHEN
		Entreprise returnedEntreprise = dao.getById(1);

		// THEN
		assertEquals(entreprise, returnedEntreprise);
	}

	@Test
	public void getEntrepriseBySiren() {
		// GIVEN
		Entreprise entreprise = new Entreprise();
		entreprise.setSiren("SIREN");
		database.getEntreprises().add(entreprise);

		// WHEN
		Entreprise returnedEntreprise = dao.getBySiren("SIREN");

		// THEN
		assertEquals(entreprise, returnedEntreprise);
	}

	@Test
	public void getAll() {
		// GIVEN
		Entreprise entreprise1 = new Entreprise();
		Entreprise entreprise2 = new Entreprise();
		database.getEntreprises().add(entreprise1);
		database.getEntreprises().add(entreprise2);

		// WHEN
		List<Entreprise> returnedEntreprises = dao.getAll();

		// THEN
		assertEquals(2, returnedEntreprises.size());
	}

	@Test
	public void deleteEntreprise() {
		// GIVEN
		Entreprise entreprise = new Entreprise();
		database.getEntreprises().add(entreprise);

		// WHEN
		dao.delete(entreprise);

		// THEN
		assertEquals(0, database.getEntreprises().size());
	}

	@Test
	public void createEntreprise() {
		// GIVEN
		Entreprise entreprise = new Entreprise();

		// WHEN
		dao.create(entreprise);

		// THEN
		assertEquals(1, database.getEntreprises().size());
	}

	@Test
	public void updateEntreprise() {
		// GIVEN
		Entreprise entreprise = new Entreprise();
		database.getEntreprises().add(entreprise);
		entreprise.setSiren("-1");

		// WHEN
		Entreprise updatedEntreprise = dao.update(entreprise);

		// THEN
		assertEquals(entreprise, updatedEntreprise);
		assertEquals("-1", updatedEntreprise.getSiren());
	}

}

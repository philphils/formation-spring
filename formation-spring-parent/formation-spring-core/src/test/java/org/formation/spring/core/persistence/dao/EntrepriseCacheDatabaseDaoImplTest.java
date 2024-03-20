package org.formation.spring.core.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.junit.After;
import org.junit.Test;

import com.github.javafaker.Faker;

public class EntrepriseCacheDatabaseDaoImplTest {

	private RandomModelGenerator generator = new RandomModelGenerator(new Faker(), new Random());

	private EntrepriseCacheDatabaseDaoImpl dao = new EntrepriseCacheDatabaseDaoImpl(CacheDatabase.access);

	@After
	public void afterEachTest() {
		CacheDatabase.access.getEntreprises().clear();
	}

	@Test
	public void getEntrepriseById() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();
		CacheDatabase.access.getEntreprises().add(entreprise);

		// WHEN
		Entreprise returnedEntreprise = dao.getById(entreprise.getId());

		// THEN
		assertEquals(entreprise, returnedEntreprise);
	}

	@Test
	public void getEntrepriseBySiren() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();
		CacheDatabase.access.getEntreprises().add(entreprise);

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
		CacheDatabase.access.getEntreprises().add(entreprise1);
		CacheDatabase.access.getEntreprises().add(entreprise2);

		// WHEN
		List<Entreprise> returnedEntreprises = dao.getAll();

		// THEN
		assertEquals(2, returnedEntreprises.size());
	}

	@Test
	public void deleteEntreprise() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();
		CacheDatabase.access.getEntreprises().add(entreprise);

		// WHEN
		dao.delete(entreprise);

		// THEN
		assertEquals(0, CacheDatabase.access.getEntreprises().size());
	}

	@Test
	public void createEntreprise() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();

		// WHEN
		dao.create(entreprise);

		// THEN
		assertEquals(1, CacheDatabase.access.getEntreprises().size());
	}

	@Test
	public void updateEntreprise() {
		// GIVEN
		Entreprise entreprise = generator.generateEntreprise();
		CacheDatabase.access.getEntreprises().add(entreprise);

		// WHEN
		entreprise.setSiren("-1");
		Entreprise updatedEntreprise = dao.update(entreprise);

		// THEN
		assertEquals(entreprise, updatedEntreprise);
		assertEquals("-1", updatedEntreprise.getSiren());
	}

}

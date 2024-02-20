package org.formation.spring.core.service;

import org.formation.spring.core.persistence.dao.EntrepriseDao;
import org.formation.spring.core.persistence.dao.EntrepriseCacheDatabaseDaoImpl;
import org.formation.spring.core.persistence.dao.ModelDao;
import org.formation.spring.core.persistence.dao.SecteurCacheDatabaseDaoImpl;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;

public class EntrepriseServiceImpl implements EntrepriseService {

	private final ModelDao<Secteur> secteurDao;

	private final ModelDao<Adresse> adresseDao;

	public EntrepriseServiceImpl(ModelDao<Adresse> adresseDao) {
		this.secteurDao = new SecteurCacheDatabaseDaoImpl();
		this.adresseDao = adresseDao;
	}

	public Entreprise createRandomEntreprise() {
		RandomModelGenerator randomModelGenerator = new RandomModelGenerator();
		Entreprise entreprise = randomModelGenerator.generateEntreprise();

		Adresse adresse = randomModelGenerator.generateAdresse();
		adresseDao.create(adresse);
		entreprise.setAdresse(adresse);

		// On crée un secteur si aucun n'existe, sinon on prend un de ceux qui existent
		Secteur secteur;
		if (secteurDao.getAll().isEmpty()) {
			secteur = randomModelGenerator.generateSecteur();
			secteurDao.create(secteur);
		} else {
			secteur = secteurDao.getAll().stream().findAny().orElseThrow();
		}
		entreprise.setSecteur(secteur);
		
		EntrepriseDao entrepriseDao = new EntrepriseCacheDatabaseDaoImpl();
		entrepriseDao.create(entreprise);

		return entreprise;
	}
}

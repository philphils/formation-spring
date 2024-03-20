package org.formation.spring.core.service;

import org.formation.spring.core.persistence.dao.EntrepriseDao;
import org.formation.spring.core.persistence.dao.ModelDao;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;

public class EntrepriseServiceImpl implements EntrepriseService {

	private final ModelDao<Secteur> secteurDao;

	private final ModelDao<Adresse> adresseDao;

	private final RandomModelGenerator randomModelGenerator;

	private final EntrepriseDao entrepriseDao;

	public EntrepriseServiceImpl(ModelDao<Secteur> secteurDao, ModelDao<Adresse> adresseDao,
			RandomModelGenerator randomModelGenerator, EntrepriseDao entrepriseDao) {
		this.secteurDao = secteurDao;
		this.adresseDao = adresseDao;
		this.randomModelGenerator = randomModelGenerator;
		this.entrepriseDao = entrepriseDao;
	}

	public Entreprise createRandomEntreprise() {
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

		entrepriseDao.create(entreprise);

		return entreprise;
	}
}

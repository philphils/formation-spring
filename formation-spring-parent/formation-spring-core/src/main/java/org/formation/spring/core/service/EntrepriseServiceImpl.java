package org.formation.spring.core.service;

import org.formation.spring.core.persistence.dao.ModelDao;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.springframework.stereotype.Service;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {

	private final ModelDao<Secteur> secteurDao;

	private final ModelDao<Adresse> adresseDao;

	private final ModelDao<Entreprise> entrepriseDao;

	private final RandomModelGenerator randomModelGenerator;

	public EntrepriseServiceImpl(ModelDao<Adresse> adresseDao, ModelDao<Secteur> secteurDao,
			ModelDao<Entreprise> entrepriseDao, RandomModelGenerator randomModelGenerator) {
		this.secteurDao = secteurDao;
		this.adresseDao = adresseDao;
		this.entrepriseDao = entrepriseDao;
		this.randomModelGenerator = randomModelGenerator;
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

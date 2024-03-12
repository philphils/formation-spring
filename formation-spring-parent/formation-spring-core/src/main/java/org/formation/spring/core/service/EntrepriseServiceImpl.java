package org.formation.spring.core.service;

import org.formation.spring.core.persistence.dao.ModelDao;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.ModelGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {

	private final ModelDao<Secteur> secteurDao;

	private final ModelDao<Adresse> adresseDao;

	private final ModelDao<Entreprise> entrepriseDao;

	// Il y a plusieurs modelGenerator instanciés par notre configuration Spring, il faut donc en choisir un
	// On peut pour cela utilsier l'annotation @Primary sur l'un d'entre eux,
	// ou définir celui qui nous intéresse ici avec son nom via l'annotation Qualifier
	@Qualifier(value = "arthur")
	private final ModelGenerator modelGenerator;

	@Autowired
	public EntrepriseServiceImpl(ModelDao<Adresse> adresseDao, ModelDao<Secteur> secteurDao,
			ModelDao<Entreprise> entrepriseDao, ModelGenerator modelGenerator) {
		this.secteurDao = secteurDao;
		this.adresseDao = adresseDao;
		this.entrepriseDao = entrepriseDao;
		this.modelGenerator = modelGenerator;
	}

	public Entreprise createNewEntreprise() {
		Entreprise entreprise = modelGenerator.generateEntreprise();

		Adresse adresse = modelGenerator.generateAdresse();
		adresseDao.create(adresse);
		entreprise.setAdresse(adresse);

		// On crée un secteur si aucun n'existe, sinon on prend un de ceux qui existent
		Secteur secteur;
		if (secteurDao.getAll().isEmpty()) {
			secteur = modelGenerator.generateSecteur();
			secteurDao.create(secteur);
		} else {
			secteur = secteurDao.getAll().stream().findAny().orElseThrow();
		}
		entreprise.setSecteur(secteur);

		entrepriseDao.create(entreprise);

		return entreprise;
	}
}

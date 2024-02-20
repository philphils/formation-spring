package org.formation.spring.core.persistence.model.generator;

import java.util.Random;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.enumeration.FormeJuridique;
import org.formation.spring.core.persistence.model.enumeration.TypeVoie;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

@Component
public class RandomModelGenerator implements ModelGenerator {

	private final Faker faker;

	private final Random random;

	public RandomModelGenerator(Faker faker, Random random) {
		this.faker = faker;
		this.random = random;
	}

	public Entreprise generateEntreprise() {

		Entreprise entreprise = new Entreprise();
		entreprise.setId(random.nextInt(Integer.MAX_VALUE));
		entreprise.setDenomination(faker.company().name());
		entreprise.setFormeJuridique(FormeJuridique.values()[random.nextInt(FormeJuridique.values().length)]);
		entreprise.setDateCreation(faker.date().birthday(2, 50));
		entreprise.setSiren(faker.number().digits(9));
		entreprise.setTelephone(faker.phoneNumber().phoneNumber());

		return entreprise;
	}

	public Adresse generateAdresse() {

		Adresse adresse = new Adresse();
		adresse.setId(random.nextInt(Integer.MAX_VALUE));
		adresse.setNomVoie(faker.address().streetSuffix());
		adresse.setNumero(faker.address().streetAddressNumber());
		adresse.setPays(faker.address().country());
		adresse.setTypeVoie(TypeVoie.values()[random.nextInt(TypeVoie.values().length)]);
		adresse.setVille(faker.address().city());

		return adresse;
	}

	public Secteur generateSecteur() {

		Secteur secteur = new Secteur();
		secteur.setId(random.nextInt(Integer.MAX_VALUE));
		secteur.setCodeNaf(faker.regexify("\\d{4}[A-Z]"));
		secteur.setLibelleNomenclature(faker.company().industry());

		return secteur;
	}

}

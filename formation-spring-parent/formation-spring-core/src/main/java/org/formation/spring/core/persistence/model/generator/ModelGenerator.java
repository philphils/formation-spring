package org.formation.spring.core.persistence.model.generator;

import java.util.Locale;
import java.util.Random;

import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.enumeration.FormeJuridique;
import org.formation.spring.core.persistence.model.enumeration.TypeVoie;

import com.github.javafaker.Faker;

public class ModelGenerator {

	public Entreprise generateEntreprise() {
		Faker faker = new Faker(Locale.FRANCE);
		Random random = new Random();

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
		Faker faker = new Faker(Locale.FRANCE);
		Random random = new Random();

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
		Faker faker = new Faker(Locale.FRANCE);
		Random random = new Random();

		Secteur secteur = new Secteur();
		secteur.setId(random.nextInt(Integer.MAX_VALUE));
		secteur.setCodeNaf(faker.regexify("\\d{4}[A-Z]"));
		secteur.setLibelleNomenclature(faker.company().industry());

		return secteur;
	}

}

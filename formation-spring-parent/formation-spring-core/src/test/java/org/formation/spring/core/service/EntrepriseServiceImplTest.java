package org.formation.spring.core.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.formation.spring.core.persistence.dao.EntrepriseDao;
import org.formation.spring.core.persistence.dao.ModelDao;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EntrepriseServiceImplTest {

	@Mock
	private ModelDao<Secteur> mockedSecteurDao;

	@Mock
	private ModelDao<Adresse> mockedAdresseDao;

	@Mock
	private EntrepriseDao mockedEntrepriseDao;

	@Mock
	private RandomModelGenerator mockedRandomModelGenerator;

	private EntrepriseService service;

	@Before
	public void setUp() {
		this.service = new EntrepriseServiceImpl(mockedSecteurDao, mockedAdresseDao, mockedRandomModelGenerator,
				mockedEntrepriseDao);

		when(mockedRandomModelGenerator.generateEntreprise()).thenReturn(new Entreprise());
		when(mockedRandomModelGenerator.generateAdresse()).thenReturn(new Adresse());
		when(mockedRandomModelGenerator.generateSecteur()).thenReturn(new Secteur());

	}

	@Test
	public void createRandomEntrepriseWhenSecteurNotExists() {
		// GIVEN

		// WHEN
		Entreprise entreprise = service.createRandomEntreprise();

		// THEN
		verify(mockedAdresseDao).create(any(Adresse.class));
		// Il n'y a pas d'injection de dépendance pour EntrepriseDao, il est crée à la
		// volée dans la méthode
		// On ne peut donc pas mocker EntrepriseDao
		// FIXME On ne peut pas vérifier qu'une entreprise est crée en base
		verify(mockedEntrepriseDao).create(any(Entreprise.class));
		// Il n'y a pas d'injection de dépendance pour SecteurDao, il est instancié dans
		// le constructeur
		// On ne peut donc pas mocker SecteurDao
		// FIXME On ne peut pas vérifier qu'un secteur a été crée en base
		verify(mockedSecteurDao).create(any(Secteur.class));

	}

	@Test
	public void createRandomEntrepriseWhenSecteurExists() {
		// GIVEN
		List<Secteur> secteurs = new ArrayList<>();
		secteurs.add(new Secteur());
		when(mockedSecteurDao.getAll()).thenReturn(secteurs);

		// WHEN
		Entreprise entreprise = service.createRandomEntreprise();

		// THEN
		verify(mockedAdresseDao).create(any(Adresse.class));
		// FIXME On ne peut pas vérifier qu'une entreprise est crée en base
		verify(mockedEntrepriseDao).create(any(Entreprise.class));
		// FIXME On ne peut pas vérifier qu'un secteur n'a pas été crée en base
		verify(mockedSecteurDao, times(0)).create(any(Secteur.class));

	}
}

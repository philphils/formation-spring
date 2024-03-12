package org.formation.spring.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.formation.spring.core.persistence.dao.ModelDao;
import org.formation.spring.core.persistence.model.Adresse;
import org.formation.spring.core.persistence.model.Entreprise;
import org.formation.spring.core.persistence.model.Secteur;
import org.formation.spring.core.persistence.model.generator.ModelGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

// Les tests unitaires ne font pas appel à Spring, il faut donc soit instancier, soit mocker les dépendances
@RunWith(MockitoJUnitRunner.class)
public class EntrepriseServiceImplTest {

	@Mock
	private ModelDao<Secteur> mockedSecteurDao;

	@Mock
	private ModelDao<Entreprise> mockedEntrepriseDao;

	@Mock
	private ModelDao<Adresse> mockedAdresseDao;

	@Mock
	private ModelGenerator mockedModelGenerator;

	private EntrepriseServiceImpl service;

	@Before
	public void setup() {
		service = new EntrepriseServiceImpl(mockedAdresseDao, mockedSecteurDao, mockedEntrepriseDao, mockedModelGenerator);

		when(mockedModelGenerator.generateEntreprise()).thenReturn(new Entreprise());
		when(mockedModelGenerator.generateAdresse()).thenReturn(new Adresse());
		when(mockedModelGenerator.generateSecteur()).thenReturn(new Secteur());
	}

	@Test
	public void createNewEntrepriseAndResultNotNull() {
		// GIVEN

		// WHEN
		Entreprise entreprise = service.createNewEntreprise();

		// THEN
		assertThat(entreprise).isNotNull();
		assertThat(entreprise.getSecteur()).isNotNull();
		assertThat(entreprise.getAdresse()).isNotNull();
	}

	@Test
	public void createNewEntrepriseWhenSecteurNotExists() {
		// GIVEN

		// WHEN
		service.createNewEntreprise();

		// THEN
		verify(mockedAdresseDao).create(any(Adresse.class));
		verify(mockedEntrepriseDao).create(any(Entreprise.class));
		verify(mockedSecteurDao).create(any(Secteur.class));
	}

	@Test
	public void createNewEntrepriseWhenSecteurExists() {
		// GIVEN
		List<Secteur> secteurs = new ArrayList<>();
		secteurs.add(new Secteur());
		when(mockedSecteurDao.getAll()).thenReturn(secteurs);

		// WHEN
		service.createNewEntreprise();

		// THEN
		verify(mockedAdresseDao).create(any(Adresse.class));
		verify(mockedEntrepriseDao).create(any(Entreprise.class));
		verify(mockedSecteurDao, times(0)).create(any(Secteur.class));

	}
}

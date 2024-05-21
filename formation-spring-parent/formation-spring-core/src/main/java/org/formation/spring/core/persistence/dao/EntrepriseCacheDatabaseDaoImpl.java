package org.formation.spring.core.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Entreprise;

public class EntrepriseCacheDatabaseDaoImpl implements EntrepriseDao {

	private final CacheDatabase cacheDatabase;
	
	public EntrepriseCacheDatabaseDaoImpl(CacheDatabase cacheDatabase) {
		this.cacheDatabase = cacheDatabase;
	}

	@Override
	public Entreprise create(Entreprise entreprise) {
		if (cacheDatabase.getEntreprises().stream().noneMatch(e -> e.getId() == entreprise.getId())) {
			cacheDatabase.getEntreprises().add(entreprise);
		} else {
			throw new IllegalArgumentException("Une entreprise avec cet id existe déjà");
		}
		return entreprise;
	}

	@Override
	public void delete(Entreprise entreprise) {
		Entreprise entrepriseASupprimer = cacheDatabase.getEntreprises()
				.stream()
				.filter(e -> e.getId() == entreprise.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec cet id n'existe"));
		cacheDatabase.getEntreprises().remove(entrepriseASupprimer);
	}

	@Override
	public Entreprise update(Entreprise entreprise) {
		return entreprise;
	}

	@Override
	public Entreprise getBySiren(String siren) {
		return cacheDatabase.getEntreprises()
				.stream()
				.filter(e -> Objects.equals(e.getSiren(), siren))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec ce siren n'existe"));
	}

	@Override
	public List<Entreprise> getAll() {
		return cacheDatabase.getEntreprises();
	}

	@Override
	public Entreprise getById(int id) {
		return cacheDatabase.getEntreprises()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec cet id n'existe"));
	}

}

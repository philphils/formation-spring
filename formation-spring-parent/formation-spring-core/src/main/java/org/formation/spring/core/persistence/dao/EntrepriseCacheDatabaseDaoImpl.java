package org.formation.spring.core.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EntrepriseCacheDatabaseDaoImpl implements EntrepriseDao {

	private CacheDatabase database;

	@Autowired
	public EntrepriseCacheDatabaseDaoImpl(CacheDatabase database) {
		this.database = database;
	}

	@Override
	public Entreprise create(Entreprise entreprise) {
		if (database.getEntreprises().stream().noneMatch(e -> e.getId() == entreprise.getId())) {
			database.getEntreprises().add(entreprise);
		} else {
			throw new IllegalArgumentException("Une entreprise avec cet id existe déjà");
		}
		return entreprise;
	}

	@Override
	public void delete(Entreprise entreprise) {
		Entreprise entrepriseASupprimer = database.getEntreprises()
				.stream()
				.filter(e -> e.getId() == entreprise.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec cet id n'existe"));
		database.getEntreprises().remove(entrepriseASupprimer);
	}

	@Override
	public Entreprise update(Entreprise entreprise) {
		return entreprise;
	}

	@Override
	public Entreprise getBySiren(String siren) {
		return database.getEntreprises()
				.stream()
				.filter(e -> Objects.equals(e.getSiren(), siren))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec ce siren n'existe"));
	}

	@Override
	public List<Entreprise> getAll() {
		return database.getEntreprises();
	}

	@Override
	public Entreprise getById(int id) {
		return database.getEntreprises()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec cet id n'existe"));
	}

}

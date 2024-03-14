package org.formation.spring.core.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Entreprise;

public class EntrepriseCacheDatabaseDaoImpl implements EntrepriseDao {

	@Override
	public Entreprise create(Entreprise entreprise) {
		if (CacheDatabase.access.getEntreprises().stream().noneMatch(e -> e.getId() == entreprise.getId())) {
			CacheDatabase.access.getEntreprises().add(entreprise);
		} else {
			throw new IllegalArgumentException("Une entreprise avec cet id existe déjà");
		}
		return entreprise;
	}

	@Override
	public void delete(Entreprise entreprise) {
		Entreprise entrepriseASupprimer = CacheDatabase.access.getEntreprises()
				.stream()
				.filter(e -> e.getId() == entreprise.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec cet id n'existe"));
		CacheDatabase.access.getEntreprises().remove(entrepriseASupprimer);
	}

	@Override
	public Entreprise update(Entreprise entreprise) {
		return entreprise;
	}

	@Override
	public Entreprise getBySiren(String siren) {
		return CacheDatabase.access.getEntreprises()
				.stream()
				.filter(e -> Objects.equals(e.getSiren(), siren))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec ce siren n'existe"));
	}

	@Override
	public List<Entreprise> getAll() {
		return CacheDatabase.access.getEntreprises();
	}

	@Override
	public Entreprise getById(int id) {
		return CacheDatabase.access.getEntreprises()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec cet id n'existe"));
	}

}

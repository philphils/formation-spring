package org.formation.spring.core.persistence.dao;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Secteur;

public class SecteurCacheDatabaseDaoImpl implements ModelDao<Secteur> {

	@Override
	public Secteur create(Secteur secteur) {
		if (CacheDatabase.access.getSecteurs().stream().noneMatch(e -> e.getId() == secteur.getId())) {
			CacheDatabase.access.getSecteurs().add(secteur);
		} else {
			throw new IllegalArgumentException("Un secteur avec cet id existe déjà");
		}
		return secteur;
	}

	@Override
	public void delete(Secteur secteur) {
		Secteur secteurASupprimer = CacheDatabase.access.getSecteurs()
				.stream()
				.filter(e -> e.getId() == secteur.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucun secteur avec cet id n'existe"));
		CacheDatabase.access.getSecteurs().remove(secteurASupprimer);
	}

	@Override
	public Secteur update(Secteur secteur) {
		return secteur;
	}

	@Override
	public List<Secteur> getAll() {
		return CacheDatabase.access.getSecteurs();
	}

	@Override
	public Secteur getById(int id) {
		return CacheDatabase.access.getSecteurs()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucun secteur avec cet id n'existe"));
	}

}

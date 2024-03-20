package org.formation.spring.core.persistence.dao;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Secteur;

public class SecteurCacheDatabaseDaoImpl implements ModelDao<Secteur> {

	private final CacheDatabase cacheDatabase;

	public SecteurCacheDatabaseDaoImpl(CacheDatabase cacheDatabase) {
		super();
		this.cacheDatabase = cacheDatabase;
	}

	@Override
	public Secteur create(Secteur secteur) {
		if (cacheDatabase.getSecteurs().stream().noneMatch(e -> e.getId() == secteur.getId())) {
			cacheDatabase.getSecteurs().add(secteur);
		} else {
			throw new IllegalArgumentException("Un secteur avec cet id existe déjà");
		}
		return secteur;
	}

	@Override
	public void delete(Secteur secteur) {
		Secteur secteurASupprimer = cacheDatabase.getSecteurs().stream().filter(e -> e.getId() == secteur.getId())
				.findAny().orElseThrow(() -> new IllegalArgumentException("Aucun secteur avec cet id n'existe"));
		cacheDatabase.getSecteurs().remove(secteurASupprimer);
	}

	@Override
	public Secteur update(Secteur secteur) {
		return secteur;
	}

	@Override
	public List<Secteur> getAll() {
		return cacheDatabase.getSecteurs();
	}

	@Override
	public Secteur getById(int id) {
		return cacheDatabase.getSecteurs().stream().filter(e -> e.getId() == id).findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucun secteur avec cet id n'existe"));
	}

}

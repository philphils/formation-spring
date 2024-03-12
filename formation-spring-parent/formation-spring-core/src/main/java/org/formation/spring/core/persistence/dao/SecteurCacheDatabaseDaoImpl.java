package org.formation.spring.core.persistence.dao;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Secteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SecteurCacheDatabaseDaoImpl implements ModelDao<Secteur> {

	private CacheDatabase database;

	@Autowired
	public SecteurCacheDatabaseDaoImpl(CacheDatabase database) {
		this.database = database;
	}

	@Override
	public Secteur create(Secteur secteur) {
		if (database.getSecteurs().stream().noneMatch(e -> e.getId() == secteur.getId())) {
			database.getSecteurs().add(secteur);
		} else {
			throw new IllegalArgumentException("Un secteur avec cet id existe déjà");
		}
		return secteur;
	}

	@Override
	public void delete(Secteur secteur) {
		Secteur secteurASupprimer = database.getSecteurs()
				.stream()
				.filter(e -> e.getId() == secteur.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucun secteur avec cet id n'existe"));
		database.getSecteurs().remove(secteurASupprimer);
	}

	@Override
	public Secteur update(Secteur secteur) {
		return secteur;
	}

	@Override
	public List<Secteur> getAll() {
		return database.getSecteurs();
	}

	@Override
	public Secteur getById(int id) {
		return database.getSecteurs()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucun secteur avec cet id n'existe"));
	}

}

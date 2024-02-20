package org.formation.spring.core.persistence.dao;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDataBase;
import org.formation.spring.core.persistence.model.Secteur;

public class SecteurCacheDatabaseDaoImpl implements ModelDao<Secteur> {

	@Override
	public Secteur create(Secteur secteur) {
		if (CacheDataBase.access.getSecteurs().stream().noneMatch(e -> e.getId() == secteur.getId())) {
			CacheDataBase.access.getSecteurs().add(secteur);
		} else {
			throw new IllegalArgumentException("Un secteur avec cet id existe déjà");
		}
		return secteur;
	}

	@Override
	public void delete(Secteur secteur) {
		Secteur secteurASupprimer = CacheDataBase.access.getSecteurs()
				.stream()
				.filter(e -> e.getId() == secteur.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucun secteur avec cet id n'existe"));
		CacheDataBase.access.getSecteurs().remove(secteurASupprimer);
	}

	@Override
	public Secteur update(Secteur secteur) {
		return secteur;
	}

	@Override
	public List<Secteur> getAll() {
		return CacheDataBase.access.getSecteurs();
	}

	@Override
	public Secteur getById(int id) {
		return CacheDataBase.access.getSecteurs()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucun secteur avec cet id n'existe"));
	}

}

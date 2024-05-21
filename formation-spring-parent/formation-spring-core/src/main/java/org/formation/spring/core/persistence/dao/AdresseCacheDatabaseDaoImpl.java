package org.formation.spring.core.persistence.dao;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Adresse;

public class AdresseCacheDatabaseDaoImpl implements ModelDao<Adresse> {

	private final CacheDatabase cacheDatabase;
	
	
	public AdresseCacheDatabaseDaoImpl(CacheDatabase cacheDatabase) {
		this.cacheDatabase = cacheDatabase;
	}

	@Override
	public Adresse create(Adresse adresse) {
		if (cacheDatabase.getAdresses().stream().noneMatch(e -> e.getId() == adresse.getId())) {
			cacheDatabase.getAdresses().add(adresse);
		} else {
			throw new IllegalArgumentException("Une adresse avec cet id existe déjà");
		}
		return adresse;
	}

	@Override
	public void delete(Adresse adresse) {
		Adresse adresseASupprimer = cacheDatabase.getAdresses()
				.stream()
				.filter(e -> e.getId() == adresse.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune adresse avec cet id n'existe"));
		cacheDatabase.getAdresses().remove(adresseASupprimer);
	}

	@Override
	public Adresse update(Adresse adresse) {
		return adresse;
	}

	@Override
	public List<Adresse> getAll() {
		return cacheDatabase.getAdresses();
	}

	@Override
	public Adresse getById(int id) {
		return cacheDatabase.getAdresses()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune adresse avec cet id n'existe"));
	}

}

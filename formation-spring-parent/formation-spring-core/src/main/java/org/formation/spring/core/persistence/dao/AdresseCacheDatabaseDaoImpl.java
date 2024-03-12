package org.formation.spring.core.persistence.dao;

import java.util.List;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdresseCacheDatabaseDaoImpl implements ModelDao<Adresse> {

	private CacheDatabase database;

	@Autowired
	public AdresseCacheDatabaseDaoImpl(CacheDatabase database) {
		this.database = database;
	}

	@Override
	public Adresse create(Adresse adresse) {
		if (database.getAdresses().stream().noneMatch(e -> e.getId() == adresse.getId())) {
			database.getAdresses().add(adresse);
		} else {
			throw new IllegalArgumentException("Une adresse avec cet id existe déjà");
		}
		return adresse;
	}

	@Override
	public void delete(Adresse adresse) {
		Adresse adresseASupprimer = database.getAdresses()
				.stream()
				.filter(e -> e.getId() == adresse.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune adresse avec cet id n'existe"));
		database.getAdresses().remove(adresseASupprimer);
	}

	@Override
	public Adresse update(Adresse adresse) {
		return adresse;
	}

	@Override
	public List<Adresse> getAll() {
		return database.getAdresses();
	}

	@Override
	public Adresse getById(int id) {
		return database.getAdresses()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune adresse avec cet id n'existe"));
	}

}

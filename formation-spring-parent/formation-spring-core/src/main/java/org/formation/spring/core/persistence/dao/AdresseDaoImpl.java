package org.formation.spring.core.persistence.dao;

import java.util.List;

import org.formation.spring.core.persistence.database.LocalDataBase;
import org.formation.spring.core.persistence.model.Adresse;

public class AdresseDaoImpl implements ModelDao<Adresse> {

	@Override
	public Adresse create(Adresse adresse) {
		if (LocalDataBase.access.getAdresses().stream().noneMatch(e -> e.getId() == adresse.getId())) {
			LocalDataBase.access.getAdresses().add(adresse);
		} else {
			throw new IllegalArgumentException("Une adresse avec cet id existe déjà");
		}
		return adresse;
	}

	@Override
	public void delete(Adresse adresse) {
		Adresse adresseASupprimer = LocalDataBase.access.getAdresses()
				.stream()
				.filter(e -> e.getId() == adresse.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune adresse avec cet id n'existe"));
		LocalDataBase.access.getAdresses().remove(adresseASupprimer);
	}

	@Override
	public Adresse update(Adresse adresse) {
		return adresse;
	}

	@Override
	public List<Adresse> getAll() {
		return LocalDataBase.access.getAdresses();
	}

	@Override
	public Adresse getById(int id) {
		return LocalDataBase.access.getAdresses()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune adresse avec cet id n'existe"));
	}

}

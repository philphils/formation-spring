package org.formation.spring.core.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.formation.spring.core.persistence.database.LocalDataBase;
import org.formation.spring.core.persistence.model.Entreprise;

public class EntrepriseDaoImpl implements EntrepriseDao {

	@Override
	public Entreprise create(Entreprise entreprise) {
		if (LocalDataBase.access.getEntreprises().stream().noneMatch(e -> e.getId() == entreprise.getId())) {
			LocalDataBase.access.getEntreprises().add(entreprise);
		} else {
			throw new IllegalArgumentException("Une entreprise avec cet id existe déjà");
		}
		return entreprise;
	}

	@Override
	public void delete(Entreprise entreprise) {
		Entreprise entrepriseASupprimer = LocalDataBase.access.getEntreprises()
				.stream()
				.filter(e -> e.getId() == entreprise.getId())
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec cet id n'existe"));
		LocalDataBase.access.getEntreprises().remove(entrepriseASupprimer);
	}

	@Override
	public Entreprise update(Entreprise entreprise) {
		return entreprise;
	}

	@Override
	public Entreprise getBySiren(String siren) {
		return LocalDataBase.access.getEntreprises()
				.stream()
				.filter(e -> Objects.equals(e.getSiren(), siren))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec ce siren n'existe"));
	}

	@Override
	public List<Entreprise> getAll() {
		return LocalDataBase.access.getEntreprises();
	}

	@Override
	public Entreprise getById(int id) {
		return LocalDataBase.access.getEntreprises()
				.stream()
				.filter(e -> e.getId() == id)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Aucune entreprise avec cet id n'existe"));
	}

}

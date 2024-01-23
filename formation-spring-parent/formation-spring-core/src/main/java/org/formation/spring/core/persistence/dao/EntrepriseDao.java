package org.formation.spring.core.persistence.dao;

import org.formation.spring.core.persistence.model.Entreprise;

public interface EntrepriseDao extends ModelDao<Entreprise> {

	public Entreprise getBySiren(String siren);

}

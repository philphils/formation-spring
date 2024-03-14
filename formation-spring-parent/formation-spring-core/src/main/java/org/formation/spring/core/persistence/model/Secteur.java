package org.formation.spring.core.persistence.model;

public class Secteur {

	private int id;

	private String codeNaf;

	private String libelleNomenclature;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeNaf() {
		return codeNaf;
	}

	public void setCodeNaf(String codeNaf) {
		this.codeNaf = codeNaf;
	}

	public String getLibelleNomenclature() {
		return libelleNomenclature;
	}

	public void setLibelleNomenclature(String libelleNomenclature) {
		this.libelleNomenclature = libelleNomenclature;
	}

}
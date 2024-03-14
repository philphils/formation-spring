package org.formation.spring.core.persistence.dao;

import java.util.List;

public interface ModelDao<T> {

	public T create(T object);

	public void delete(T object);

	public T update(T object);

	public T getById(int id);

	public List<T> getAll();

}

package xaw.rest_services.domain.infrastructure_service;

import java.util.List;
import java.util.Map;

import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface IGenericRepository<T, PK> {

	T create(T o) throws UnexpectedPersistenceException;

	T update(T o) throws UnexpectedPersistenceException;

	void delete(T o) throws UnexpectedPersistenceException;

	T load(PK id) throws UnexpectedPersistenceException;

	List<T> findByQuery(String jpql, Map<String, Object> parameters,
			Integer page, Integer size) throws UnexpectedPersistenceException;

}

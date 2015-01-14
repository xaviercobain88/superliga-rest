package xaw.rest_services.infrastructure.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public class GenericRepository<T, PK extends Serializable> {

	public static final int SIZE = 20;
	public static final int PAGE = 1;
	public static final int MAZ_SIZE = 100;

	private Class<T> type;

	@PersistenceContext(unitName = "restServicesPU")
	protected EntityManager entityManager;

	public GenericRepository(final Class<T> type) {

		this.type = type;
	}

	public T create(T o) throws UnexpectedPersistenceException {
		try {
			entityManager.persist(o);
			entityManager.flush();
			entityManager.refresh(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedPersistenceException(e.getMessage());
		}
	}

	public T update(T o) throws UnexpectedPersistenceException {
		try {
			entityManager.merge(o);
			return o;
		} catch (Exception e) {
			throw new UnexpectedPersistenceException(e.getMessage());
		}
	}

	public void delete(T o) throws UnexpectedPersistenceException {
		try {
			o = entityManager.merge(o);
			entityManager.remove(o);

		} catch (Exception e) {
			throw new UnexpectedPersistenceException(e.getMessage());
		}

	}

	public T load(PK id) throws UnexpectedPersistenceException {
		try {
			return entityManager.find(type, id);
		} catch (Exception e) {
			throw new UnexpectedPersistenceException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByQuery(String jpql, Map<String, Object> parameters,
			Integer page, Integer size) throws UnexpectedPersistenceException {
		try {
			Set<Entry<String, Object>> rawParameters = parameters.entrySet();
			Query query = entityManager.createQuery(jpql);
			if (size != null && size > 0) {
				query.setMaxResults(size);
				if (page != null && page > 0) {
					query.setFirstResult((page - 1) * size);
				}
			}

			for (Entry<String, Object> entry : rawParameters) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedPersistenceException();
		}
	}

	public List<T> findByQuery(String jpql, Map<String, Object> parameters)
			throws UnexpectedPersistenceException {
		return findByQuery(jpql, parameters, PAGE, MAZ_SIZE);
	}

}

package xaw.rest_services.infrastructure.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import xaw.rest_services.infrastructure.helper.PersistenceUnitConstant;
@Stateless
public class PersistenceUnitHelper {
	@PersistenceContext(unitName = PersistenceUnitConstant.REST_SERVICE_PU, name="jpa/pc")
	protected EntityManager entityManager;
}

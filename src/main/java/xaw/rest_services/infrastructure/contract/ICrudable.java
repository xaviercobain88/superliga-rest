package xaw.rest_services.infrastructure.contract;

import java.io.Serializable;

import xaw.rest_services.domain.exception.DomainModelNotCreatedException;
import xaw.rest_services.domain.exception.DomainModelNotDeletedException;
import xaw.rest_services.domain.exception.DomainModelNotLoadedException;
import xaw.rest_services.domain.exception.DomainModelNotUpdatedException;

public interface ICrudable<T, PK extends Serializable> {

	T create() throws DomainModelNotCreatedException;

	T update() throws DomainModelNotUpdatedException;

	void delete() throws DomainModelNotDeletedException;

	T load(PK id) throws DomainModelNotLoadedException;

}

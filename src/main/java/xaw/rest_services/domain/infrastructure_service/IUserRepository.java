package xaw.rest_services.domain.infrastructure_service;

import xaw.rest_services.domain.model.User;
import xaw.rest_services.infrastructure.exception.UnexpectedPersistenceException;

public interface IUserRepository extends IGenericRepository<User, Long> {


}

package core.domain.contract;

import core.domain.enums.StatusEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import utils.exception.InvalidArgumentException;

public interface IUserRepository extends IGenericRepository<User, Long> {

    User loadWithAllPermission(Long id) throws UnexpectedPersistenceException, InvalidArgumentException, DomainModelNotLoadedException;

    public User findByUsername(String username, StatusEnum status) throws UnexpectedPersistenceException, DomainModelNotLoadedException;

    public User findByToken(String Token) throws UnexpectedPersistenceException, DomainModelNotLoadedException;

    User findByUsername(String username) throws UnexpectedPersistenceException, DomainModelNotLoadedException;
}

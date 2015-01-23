package core.domain.imp;

import core.domain.contract.IUserHandler;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.infrastructure_service.IUserRepository;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavier on 1/22/15.
 */
@Stateless
public class UserHandler implements IUserHandler{
    @Inject
    protected IUserRepository userRepository;
    @Override
    public List<Long> getTeamIdsOfUserPlayers(Long userId) throws InvalidArgumentException, UnexpectedPersistenceException, DomainModelNotLoadedException {

        return null;
    }
}

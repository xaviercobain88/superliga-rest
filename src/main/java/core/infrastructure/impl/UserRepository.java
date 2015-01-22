package core.infrastructure.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import core.domain.exception.DomainModelNotLoadedException;
import core.domain.infrastructure_service.IPlayerRepository;
import core.domain.infrastructure_service.IUserRepository;
import core.domain.interfaces.SecuredManageable;
import core.domain.model.Player;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import utils.exception.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserRepository extends GenericRepository<User, Long> implements
		IUserRepository {


	@EJB
	protected IPlayerRepository playerRepository;

	public UserRepository() {
		super(User.class);
	}

	public User loadWithAllPermission(Long id) throws UnexpectedPersistenceException, InvalidArgumentException, DomainModelNotLoadedException {
		if(id==null || id < 1){
			throw new InvalidArgumentException("Invalid arguments, id cannot be null or lt 1");
		}

		User user = load(id);
		if(user==null){
			throw new DomainModelNotLoadedException("There is no user with id: "+id);
		}

		ArrayList<Player> players = (ArrayList<Player>) playerRepository.findByUserId(id);

		for(SecuredManageable securedManageable: players){
			user.addSecureManageable(securedManageable);
		}

		return user;
	}


}

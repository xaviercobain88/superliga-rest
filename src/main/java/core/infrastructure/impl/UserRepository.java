package core.infrastructure.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import core.domain.enums.UserStatusEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.contract.IPlayerRepository;
import core.domain.contract.IUserRepository;
import security.domain.contract.SecuredManageable;
import core.domain.model.Player;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import utils.exception.InvalidArgumentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class UserRepository extends GenericRepository<User, Long> implements
		IUserRepository {


	@Inject
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
		user.addSecureManageable(user);

		return user;
	}

	@Override
	public User findByUsername(String username, UserStatusEnum status) throws UnexpectedPersistenceException, DomainModelNotLoadedException {
		String jpql = "select u from User u where u.username=:username ";
		if(status!=null){
			jpql+=	" and u.status = :status";
		}
		HashMap<String, Object> params = new HashMap<>();
		params.put("username", username);
		if(status!=null){
			params.put("status", status);
		}

		List<User> users = findByQuery(jpql, params);
		if(users!=null && !users.isEmpty()){
			return users.get(0);
		}
		throw  new DomainModelNotLoadedException("User with provided username not found");

	}

	public User findByUsername(String username) throws UnexpectedPersistenceException, DomainModelNotLoadedException{
		return findByUsername(username, UserStatusEnum.ACTIVE);
	}

	@Override
	public User findByUsernameWithoutStatus(String username) throws UnexpectedPersistenceException, DomainModelNotLoadedException {
		return findByUsername(username, null);
	}

	@Override
	public User findByToken(String token) throws UnexpectedPersistenceException, DomainModelNotLoadedException {
		String jpql = "select t.user from DistributedServiceToken t where t.token=:token";
		HashMap<String, Object> params = new HashMap<>();
		params.put("token", token);
		List<User> users = findByQuery(jpql, params);
		if(users!=null && !users.isEmpty()){
			return users.get(0);
		}
		throw  new DomainModelNotLoadedException("User with provided token not found");
	}


}

package core.domain.infrastructure_service;

import core.domain.model.Player;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.List;

@Local
public interface IPlayerRepository extends IGenericRepository<Player, Long> {

    public List<Player> findByUserId(Long userId) throws UnexpectedPersistenceException;

}

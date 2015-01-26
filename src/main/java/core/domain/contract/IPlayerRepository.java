package core.domain.contract;

import core.domain.model.Player;
import core.infrastructure.exception.UnexpectedPersistenceException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPlayerRepository extends IGenericRepository<Player, Long> {

    public List<Player> findByUserId(Long userId) throws UnexpectedPersistenceException;

}

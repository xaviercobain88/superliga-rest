package core.infrastructure.impl;

import core.domain.enums.GenericStatusEnum;
import core.domain.contract.IPlayerRepository;
import core.domain.model.Player;
import core.infrastructure.exception.UnexpectedPersistenceException;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
@Stateless
public class PlayerRepository extends GenericRepository<Player, Long> implements IPlayerRepository{
    public PlayerRepository() {
        super(Player.class);
    }

    @Override
    public List<Player> findByUserId(Long userId) throws UnexpectedPersistenceException {
        String jpql = "select p from Player p where p.user.id = :userId and p.status = :status";
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        GenericStatusEnum status = GenericStatusEnum.ACTIVE;
        params.put("status", status);
        return findByQuery(jpql, params);
    }
}

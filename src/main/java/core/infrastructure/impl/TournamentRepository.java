package core.infrastructure.impl;

import core.domain.contract.ITournamentRepository;
import core.domain.model.Tournament;

import javax.ejb.Stateless;

/**
 * Created by xavier on 1/24/15.
 */
@Stateless
public class TournamentRepository extends GenericRepository<Tournament, Long> implements ITournamentRepository{

    public TournamentRepository() {
        super(Tournament.class);
    }
}

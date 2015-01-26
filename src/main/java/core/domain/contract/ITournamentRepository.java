package core.domain.contract;

import core.domain.model.Tournament;

import javax.ejb.Local;

/**
 * Created by xavier on 1/24/15.
 */
@Local
public interface ITournamentRepository extends IGenericRepository<Tournament, Long> {
}

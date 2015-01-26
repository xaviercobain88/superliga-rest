package core.domain.impl;

import core.domain.contract.IModelAdminRepository;
import core.domain.contract.ITeamRepository;
import core.domain.contract.ITournamentHandler;
import core.domain.contract.ITournamentRepository;
import core.domain.model.Tournament;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.enums.SecuredManageableTypeEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by xavier on 1/25/15.
 */
@Stateless
public class TournamentHandler implements ITournamentHandler{
    @Inject
    protected ITournamentRepository tournamentRepository;
    @Inject
    protected IModelAdminRepository modelAdminRepository;

    @Override
    public Tournament create(@Min(1) Long userId, @Valid Tournament tournament) throws UnexpectedPersistenceException {
        tournamentRepository.create(tournament);
        modelAdminRepository.create(userId, tournament.getId(), SecuredManageableTypeEnum.TOURNAMENT);
        return tournament;
    }
}

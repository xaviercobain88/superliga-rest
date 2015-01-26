package core.application.imp;

import core.application.contract.ITournamentService;
import core.application.dto.TournamentDTO;
import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import core.domain.contract.ITournamentHandler;
import core.domain.model.Tournament;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.apache.commons.beanutils.BeanUtils;
import security.aop.SecuredModel;
import security.domain.enums.SecuredManageableTypeEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by xavier on 1/24/15.
 */
@Stateless

public class TournamentService implements ITournamentService {

    @Inject
    protected ITournamentHandler tournamentHandler;

    @Override
    @SecuredModel(securedManageableTypes = SecuredManageableTypeEnum.USER)

    public TournamentDTO create(@NotNull @Min(1) Long userId, @Valid TournamentDTO tournamentDTO) throws InternalServerErrorException, UnauthorizedException {
        Tournament tournament = new Tournament(tournamentDTO.getName(), tournamentDTO.getDiscipline());

        try {
            tournamentHandler.create(userId, tournament);
            TournamentDTO newTournamentDTO = new TournamentDTO();
            BeanUtils.copyProperties(newTournamentDTO, tournament);
            return newTournamentDTO;
        } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }
}

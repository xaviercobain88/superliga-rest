package core.domain.impl;

import core.domain.contract.*;
import core.domain.enums.PlayingModeEnum;
import core.domain.exception.InvalidArgumentsForTournamentSetupException;
import core.domain.model.Group;
import core.domain.model.Stage;
import core.domain.model.Tournament;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.hibernate.validator.constraints.NotEmpty;
import security.domain.enums.SecuredManageableTypeEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created by xavier on 1/25/15.
 */
@Stateless
public class TournamentHandler implements ITournamentHandler{
    @Inject
    protected ITournamentRepository tournamentRepository;
    @Inject
    protected IModelAdminRepository modelAdminRepository;
    @Inject
    protected IStageRepository stageRepository;
    @Inject
    protected  IGroupRepository groupRepository;

    @Override
    public Tournament create(@Min(1) Long userId, @Valid Tournament tournament) throws UnexpectedPersistenceException {
        tournamentRepository.create(tournament);
        modelAdminRepository.create(userId, tournament.getId(), SecuredManageableTypeEnum.TOURNAMENT);
        return tournament;
    }

    @Override
    public Stage setStages(@Min(1) Long tournamentId, @Valid @NotEmpty List<Stage> stages)
            throws InvalidArgumentsForTournamentSetupException, UnexpectedPersistenceException {




        //Saved first in order to auto-generate id
        for (Stage stage : stages){
            if(!stage.isValid()){
                throw new InvalidArgumentsForTournamentSetupException("Setup configurations is wrong");
            }
            stageRepository.create(stage);
            stage.createGroups();
            for (Group group: stage.getGroups()){
                groupRepository.create(group);
            }

        }
        //Now, we have an id for all stages, so we can set the next stage attribute for each stage
        Integer totalStages = stages.size();
        Integer stagesCount = 0;
        for (Stage stage : stages){
            stagesCount++;
            if(stagesCount<totalStages){
                stage.setNextStage(stages.get(stagesCount));
                stageRepository.update(stage);
            }
        }









        //TODO: Verificar si los modos de juego coinciden con el input y output
        //TODO: Crear los output target
        //TODO: Crear los sources
        return null;
    }
}

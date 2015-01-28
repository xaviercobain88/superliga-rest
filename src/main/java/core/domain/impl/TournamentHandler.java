package core.domain.impl;

import core.domain.contract.*;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavier on 1/25/15.
 */
@Stateless
public class TournamentHandler implements ITournamentHandler {
    @Inject
    protected ITournamentRepository tournamentRepository;
    @Inject
    protected IModelAdminRepository modelAdminRepository;
    @Inject
    protected IStageRepository stageRepository;
    @Inject
    protected IGroupRepository groupRepository;

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
        for (Stage stage : stages) {
            if (!stage.isValid()) {
                throw new InvalidArgumentsForTournamentSetupException("Setup configurations is wrong");
            }
            stageRepository.create(stage);
            stage.createGroups();
            for (Group group : stage.getGroups()) {
                groupRepository.create(group);
            }

        }
        //Now, we have an id for all stages, so we can set the next stage attribute for each stage
        Integer totalStages = stages.size();
        Integer stagesCount = 0;
        Integer inputTeams = 0;
        Integer outputTeams = 0;
        List<Stage> previousSumStages = new ArrayList<>();
        List<Stage> currentSumStages = new ArrayList<>();
        for (Stage stage : stages) {
            if (currentSumStages == null ||  currentSumStages.isEmpty() ) {
                currentSumStages.add(stage);
            } else if ((stage.getInputTeams().equals(inputTeams)
                    && getTotalOutput(currentSumStages) < stage.getInputTeams())) {
                stage.setInputSourceStages(previousSumStages);
                setOutputTargetStages(previousSumStages, stage);
                currentSumStages.add(stage);
            } else if (stage.getInputTeams().equals(getTotalOutput(currentSumStages))) {
                stage.setInputSourceStages(currentSumStages);
                setOutputTargetStages(currentSumStages, stage);
                previousSumStages = currentSumStages;
                currentSumStages.clear();
                currentSumStages.add(stage);
            } else if (stage.getInputTeams().equals(outputTeams)) {
                stage.setInputSourceStages(currentSumStages);
                setOutputTargetStages(currentSumStages, stage);
                previousSumStages = currentSumStages;
                currentSumStages.clear();
                currentSumStages.add(stage);
            } else{
                throw new InvalidArgumentsForTournamentSetupException("Setup configurations is wrong");
            }
            inputTeams = stage.getInputTeams();
            outputTeams = stage.getOutputTeams();

            stagesCount++;
            if (stagesCount < totalStages) {
                stage.setNextStage(stages.get(stagesCount));

            }
        }
        for (Stage stage : stages) {
            stageRepository.update(stage);
        }


        //TODO: Verificar si los modos de juego coinciden con el input y output
        //TODO: Crear los output target
        //TODO: Crear los sources
        return null;
    }

    private Integer getTotalOutput(List<Stage> outputStages) {
        if (outputStages == null) {
            return 0;
        }
        Integer total = 0;
        for (Stage stage : outputStages) {
            total += stage.getOutputTeams();
        }
        return total;
    }

    private void setOutputTargetStages(List<Stage> stages, Stage targetStage) {

        if (targetStage == null || stages == null) {
            return;
        }
        for (Stage stage : stages) {
            stage.addOutputTargetStage(targetStage);
        }
    }
}

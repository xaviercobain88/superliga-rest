package core.domain.impl;

import core.domain.contract.*;
import core.domain.enums.InvitationStatusEnum;
import core.domain.enums.InvitationTypeEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.exception.InvalidArgumentsForTournamentSetupException;
import core.domain.exception.InvalidInvitationException;
import core.domain.model.*;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import security.domain.enums.SecuredManageableTypeEnum;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Inject
    protected IUserRepository userRepository;
    @Inject
    protected IInvitationRepository invitationRepository;

    @Override
    public Tournament create(@Min(1) Long userId, @Valid Tournament tournament) throws UnexpectedPersistenceException {
        tournamentRepository.create(tournament);
        modelAdminRepository.create(userId, tournament.getId(), SecuredManageableTypeEnum.TOURNAMENT);
        return tournament;
    }

    @Override
    public List<User> sendInvitations(@Min(1) Long tournamentId, @NotEmpty Set<String> emails, @NotNull User sender)
            throws UnexpectedPersistenceException, DomainModelNotLoadedException,  InvalidInvitationException {
        ArrayList<User> invitedUsers = new ArrayList<>();
        ArrayList<InvitationStatusEnum> statuses = new ArrayList<>();
        statuses.add(InvitationStatusEnum.ACCEPTED);
        statuses.add(InvitationStatusEnum.PENDING);
        ArrayList<Invitation> invitations = (ArrayList<Invitation>) invitationRepository.findByResource(tournamentId, InvitationTypeEnum.TOURNAMENT_TEAM, statuses);
        Integer totalInvitations = 0;
        if (invitations != null && !invitations.isEmpty()) {
            totalInvitations = invitations.size();
        }
        Tournament tournament = tournamentRepository.load(tournamentId);
        if (tournament == null) {
            throw new DomainModelNotLoadedException("Tournament not found");
        }
        Integer availableInvitations = tournament.getInputTeams() - totalInvitations;
        if (availableInvitations < emails.size()) {
            throw new InvalidInvitationException("Invitations exceed available invitations");
        }

        for (String email : emails) {
            if (isValidEmail(tournamentId, InvitationTypeEnum.TOURNAMENT_TEAM, email)) {
                User user = new User();
                try {
                    user = userRepository.findByUsernameWithoutStatus(email);
                } catch (DomainModelNotLoadedException e) {
                    user.setInvitedUser(email);
                    userRepository.create(user);
                }


                Invitation invitation = new Invitation(sender, user, InvitationTypeEnum.TOURNAMENT_TEAM, tournamentId);
                invitationRepository.create(invitation);
                invitedUsers.add(user);
            }

        }
        return invitedUsers;
    }

    private boolean isValidEmail(@Min(1) Long polymorphicResourceId, @NotNull InvitationTypeEnum type,
                                 @NotBlank String email) throws UnexpectedPersistenceException {
        ArrayList<InvitationStatusEnum> statuses = new ArrayList<>();
        statuses.add(InvitationStatusEnum.ACCEPTED);
        statuses.add(InvitationStatusEnum.PENDING);

        User user = null;
        try {
            user = userRepository.findByUsername(email, null);
        } catch (DomainModelNotLoadedException e) {
            return true;
        }
        List<Invitation> invitations = invitationRepository.findByResourceAndEmail(polymorphicResourceId, type, statuses, user.getId());
        if (invitations == null || invitations.isEmpty()) {
            return true;
        }
        return false;

    }

    @Override
    public Stage setStages(@Min(1) Long tournamentId, @Valid @NotEmpty List<Stage> stages)
            throws InvalidArgumentsForTournamentSetupException, UnexpectedPersistenceException, DomainModelNotLoadedException {


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
        Tournament tournament = tournamentRepository.load(tournamentId);
        if (tournament == null) {
            throw new DomainModelNotLoadedException("Tournament not found");
        }

        tournament.setInputTeams(stages.get(0).getInputTeams());
        tournamentRepository.update(tournament);

        for (Stage stage : stages) {
            stage.setTournament(tournament);
            if (currentSumStages == null || currentSumStages.isEmpty()) {
                currentSumStages.add(stage);
            } else if ((stage.getInputTeams().equals(inputTeams)
                    && getTotalOutput(currentSumStages) < stage.getInputTeams())) {
                //stage.setInputSourceStages(previousSumStages);
                setOutputTargetStages(previousSumStages, stage);
                currentSumStages.add(stage);
            } else if (stage.getInputTeams().equals(getTotalOutput(currentSumStages))) {
                //stage.setInputSourceStages(currentSumStages);
                setOutputTargetStages(currentSumStages, stage);
                previousSumStages = new ArrayList<>(currentSumStages);
                currentSumStages = new ArrayList<>();
                currentSumStages.add(stage);
            } else if (stage.getInputTeams().equals(outputTeams)) {
                //stage.setInputSourceStages(currentSumStages);
                setOutputTargetStages(currentSumStages, stage);
                previousSumStages = new ArrayList<>(currentSumStages);
                currentSumStages = new ArrayList<>();
                currentSumStages.add(stage);
            } else {
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

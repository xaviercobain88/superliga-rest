package core.domain.impl;

import core.domain.contract.*;
import core.domain.enums.InvitationStatusEnum;
import core.domain.enums.InvitationTypeEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.exception.InvalidInvitationException;
import core.domain.model.*;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.enums.SecuredManageableTypeEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.Min;

/**
 * Created by xavier on 1/25/15.
 */
@Stateless
public class InvitationHandler implements IInvitationHandler {
    @Inject
    protected IInvitationRepository invitationRepository;
    @Inject
    protected ITournamentRepository tournamentRepository;
    @Inject
    protected IUserRepository userRepository;
    @Inject
    protected IModelAdminRepository modelAdminRepository;
    @Inject
    protected ITeamRepository teamRepository;
    @Inject
    protected IPlayerRepository playerRepository;


    @Override
    public Team acceptTournamentParticipation(@Min(1) Long invitationId)
            throws UnexpectedPersistenceException, InvalidInvitationException, DomainModelNotLoadedException {

        Invitation invitation = invitationRepository.load(invitationId);
        if (invitation == null) {
            throw new DomainModelNotLoadedException("There is no invitation with the provided id");
        }
        Tournament tournament = null;
        if (invitation.getPolymorphicResourceId() != null) {
            tournament = tournamentRepository.load(invitation.getPolymorphicResourceId());
        }
        if (!(invitation.isValidStatus(InvitationStatusEnum.PENDING) && invitation.isValidType(InvitationTypeEnum.TOURNAMENT_TEAM) && tournament != null)) {
            throw new InvalidInvitationException("Invitation has no valid type, status or tournament");
        }
        invitation.acceptInvitation();
        Team team = new Team();
        team.addTournament(tournament);
        if (invitation.getRecipient() == null) {
            throw new DomainModelNotLoadedException("There is user for this invitation");
        }

        User recipient = invitation.getRecipient();

        teamRepository.create(team);
        if (tournament.isForTeam()) {
            Player player = new Player(team, recipient);
            playerRepository.create(player);
        }
        invitationRepository.update(invitation);
        modelAdminRepository.create(recipient.getId(), team.getId(), SecuredManageableTypeEnum.TEAM);
        return team;

    }
    @Override
    public void refuseTournamentParticipation(@Min(1) Long invitationId) throws UnexpectedPersistenceException, DomainModelNotLoadedException, InvalidInvitationException {

        Invitation invitation = invitationRepository.load(invitationId);
        if (invitation == null) {
            throw new DomainModelNotLoadedException("There is no invitation with the provided id");
        }
        if (!(invitation.isValidStatus(InvitationStatusEnum.PENDING) && invitation.isValidType(InvitationTypeEnum.TOURNAMENT_TEAM) )) {
            throw new InvalidInvitationException("Invitation has no valid type, status or tournament");
        }
        invitation.refuseInvitation();
        invitationRepository.update(invitation);

    }
}

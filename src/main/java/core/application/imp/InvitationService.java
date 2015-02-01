package core.application.imp;

import core.application.contract.IInvitationService;
import core.application.dto.InvitationDTO;
import core.application.dto.TeamDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.domain.contract.IInvitationHandler;
import core.domain.contract.IInvitationRepository;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.exception.InvalidInvitationException;
import core.domain.model.Invitation;
import core.domain.model.Team;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.apache.commons.beanutils.BeanUtils;
import security.aop.SecuredInvitation;
import security.domain.enums.InvitationSourceEnum;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by xavier on 1/24/15.
 */
public class InvitationService implements IInvitationService {


    @Inject
    protected IInvitationHandler invitationHandler;
    @Inject
    protected IInvitationRepository invitationRepository;
    @Override
    @SecuredInvitation(extrict = InvitationSourceEnum.RECIPIENT)
    public TeamDTO acceptTournamentParticipation(@Min(1) Long invitationId) throws InternalServerErrorException, BadRequestException {

        try {
            Team team = invitationHandler.acceptTournamentParticipation(invitationId);
            TeamDTO teamDTO = new TeamDTO();
            BeanUtils.copyProperties(teamDTO, team);
            return teamDTO;
        } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        } catch (InvalidInvitationException | DomainModelNotLoadedException e) {
            throw new BadRequestException("La invitación no es actualmente válida");
        }
    }

    @Override
    @SecuredInvitation
    public boolean refuseTournamentParticipation(@Min(1) Long invitationId) throws InternalServerErrorException, BadRequestException {
        try {
            invitationHandler.refuseTournamentParticipation(invitationId);
        } catch (UnexpectedPersistenceException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }  catch (InvalidInvitationException | DomainModelNotLoadedException e) {
            throw new BadRequestException("La invitación no es actualmente válida");
        }
        return true;
    }

    @Override
    public InvitationDTO load(@Min(1) Long invitationId) throws InternalServerErrorException {
        InvitationDTO invitationDTO = new InvitationDTO();
        try {
            Invitation invitation = invitationRepository.load(invitationId);
            if(invitation!=null && invitation.getRecipient()!=null && invitation.getSender()!=null){
                BeanUtils.copyProperties(invitationDTO, invitation);
                invitationDTO.setRecipientId(invitation.getRecipient().getId());
                invitationDTO.setSenderId(invitation.getSender().getId());
                return invitationDTO;
            }else{
                throw new InternalServerErrorException();
            }
        } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }

    }
}

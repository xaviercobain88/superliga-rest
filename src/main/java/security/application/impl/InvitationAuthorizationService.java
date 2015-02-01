package security.application.impl;

import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.domain.exception.DomainModelNotLoadedException;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.application.contract.IInvitationAuthorizationService;
import security.domain.contract.IInvitationAuthorizationHandler;
import security.domain.enums.InvitationSourceEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by xavier on 1/21/15.
 */
@Stateless
public class InvitationAuthorizationService implements IInvitationAuthorizationService {

    @Inject
    protected IInvitationAuthorizationHandler invitationAuthorizationHandler;

    @Override
    public boolean isAllowed(@Min(1) Long invitationId, @Min(1) Long userId, @NotNull InvitationSourceEnum source)
            throws InternalServerErrorException, BadRequestException {
        try {
            return invitationAuthorizationHandler.isAllowed(invitationId, userId, source);
        } catch (UnexpectedPersistenceException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        } catch (DomainModelNotLoadedException e) {
            e.printStackTrace();
            throw new BadRequestException("La invitación no es actualmente válida");
        }
    }
}

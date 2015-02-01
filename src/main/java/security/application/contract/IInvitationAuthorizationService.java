package security.application.contract;

import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import security.domain.enums.InvitationSourceEnum;

import javax.ejb.Local;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by xavier on 1/21/15.
 */
@Local
public interface IInvitationAuthorizationService {
    boolean isAllowed(@Min(1) Long invitationId, @Min(1) Long userId, @NotNull InvitationSourceEnum source) throws InternalServerErrorException, BadRequestException;

}

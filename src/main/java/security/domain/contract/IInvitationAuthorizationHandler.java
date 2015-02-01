package security.domain.contract;

import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.domain.exception.DomainModelNotLoadedException;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.enums.InvitationSourceEnum;
import security.domain.enums.SecuredManageableTypeEnum;
import utils.exception.InvalidArgumentException;

import javax.ejb.Local;
import javax.persistence.MapsId;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
@Local
public interface IInvitationAuthorizationHandler {
    boolean isAllowed(@Min(1) Long invitationId, @Min(1) Long userId, @NotNull InvitationSourceEnum source) throws UnexpectedPersistenceException, DomainModelNotLoadedException;

}

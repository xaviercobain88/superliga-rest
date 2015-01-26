package security.domain.contract;

import security.domain.enums.SecuredManageableTypeEnum;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import utils.exception.InvalidArgumentException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
@Local
public interface IModelAuthorizationHandler {
    boolean isAllowed(List<SecuredManageableTypeEnum> securedManageableTypes, Long manageableResourceId, Long userId) throws UnexpectedPersistenceException, InvalidArgumentException;

}

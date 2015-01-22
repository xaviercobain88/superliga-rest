package security.domain.contract;

import core.domain.enums.SecuredManageableTypeEnum;
import core.domain.model.User;

import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
public interface IModelAuthorizationHandler {
    boolean isAllowed(List<SecuredManageableTypeEnum> securedManageableTypes, Long manageableResourceId, User user);

}

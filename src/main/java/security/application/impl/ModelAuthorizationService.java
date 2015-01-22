package security.application.impl;

import core.domain.enums.SecuredManageableTypeEnum;
import core.domain.model.User;
import security.aop.LoggedUser;
import security.application.contract.IModelAuthorizationService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
public class ModelAuthorizationService implements IModelAuthorizationService {
    @Inject
    @LoggedUser
    protected User user;
    @Override
    public boolean isAllowed(List<SecuredManageableTypeEnum> securedManageableTypes, Long id) {

        return false;
    }
}

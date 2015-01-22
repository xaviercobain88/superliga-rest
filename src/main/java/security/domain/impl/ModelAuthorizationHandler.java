package security.domain.impl;

import core.domain.enums.SecuredManageableTypeEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.infrastructure_service.IUserRepository;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.contract.IModelAuthorizationHandler;
import utils.exception.InvalidArgumentException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
public class ModelAuthorizationHandler implements IModelAuthorizationHandler{

    @Inject
    protected IUserRepository userRepository;

    @Override
    public boolean isAllowed(List<SecuredManageableTypeEnum> securedManageableTypes, Long manageableResourceId, User user) {
        if(user==null || user.getId()<1 || securedManageableTypes==null || securedManageableTypes.isEmpty()){
            return false;
        }
        //TODO: verificar que el user que llega como parametro no se desatacha
        try {
            User userWithManageablePermissions = userRepository.loadWithAllPermission(user.getId());
            if(securedManageableTypes.size()==1){
                return userWithManageablePermissions.isManageable(manageableResourceId, securedManageableTypes.get(0));
            }

            return true;

        } catch (UnexpectedPersistenceException|InvalidArgumentException | DomainModelNotLoadedException e) {
            e.printStackTrace();
            return false;
        }
    }
}

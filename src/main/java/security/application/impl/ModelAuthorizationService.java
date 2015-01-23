package security.application.impl;

import core.application.exception.InternalServerErrorException;
import core.domain.enums.SecuredManageableTypeEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.aop.LoggedUser;
import security.application.contract.IModelAuthorizationService;
import security.domain.contract.IModelAuthorizationHandler;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
@Stateless
public class ModelAuthorizationService implements IModelAuthorizationService {
    @Inject
    @LoggedUser
    protected User user;
    @Inject
    protected IModelAuthorizationHandler modelAuthorizationHandler;

    @Override
    public boolean isAllowed(List<SecuredManageableTypeEnum> securedManageableTypes, Long id) throws InternalServerErrorException {

        if(user==null || user.getId()==null || user.getId()<1){
            return false;
        }
        try {
            return modelAuthorizationHandler.isAllowed(securedManageableTypes, id, user);
        } catch (UnexpectedPersistenceException | InvalidArgumentException  e) {
            e.printStackTrace();
            throw  new InternalServerErrorException("Hubo un error en el sistema, por favor intente más tarde");
        }
    }
}

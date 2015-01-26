package security.application.impl;

import core.application.exception.InternalServerErrorException;
import security.application.dto.UserDTO;
import security.domain.enums.SecuredManageableTypeEnum;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.aop.LoggedUser;
import security.application.contract.IModelAuthorizationService;
import security.domain.contract.IModelAuthorizationHandler;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by xavier on 1/21/15.
 */
@Stateless
public class ModelAuthorizationService implements IModelAuthorizationService {

    @Inject
    @LoggedUser
    protected UserDTO user;
    @Inject
    protected IModelAuthorizationHandler modelAuthorizationHandler;
    @Inject
    Logger logger;

    @Override
    public boolean isAllowed(List<SecuredManageableTypeEnum> securedManageableTypes, Long id) throws InternalServerErrorException {



        try {
            logger.warning("Si entra");
            return modelAuthorizationHandler.isAllowed(securedManageableTypes, id, user.getId());
        } catch (UnexpectedPersistenceException | InvalidArgumentException  e) {
            e.printStackTrace();
            throw  new InternalServerErrorException();
        }
    }
}

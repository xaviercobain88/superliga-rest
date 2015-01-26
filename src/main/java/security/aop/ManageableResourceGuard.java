package security.aop;

import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import core.domain.model.User;
import security.application.contract.IModelAuthorizationService;
import security.application.dto.UserDTO;
import security.domain.enums.SecuredManageableTypeEnum;
import sun.rmi.runtime.Log;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by xavier on 1/20/15.
 */
@SecuredModel
@Interceptor

public class ManageableResourceGuard {

    @Inject
    protected IModelAuthorizationService secureManageableService;
    @Inject
    @LoggedUser
    Instance<UserDTO> users;
    @Inject
    Logger logger;


    @AroundInvoke
    public Object validatePermissions(InvocationContext ic) throws Exception {
        UserDTO user = users.get();
        if (!isAllowed(ic, user)) {
            throw new UnauthorizedException("No tienes permisos para realizar esta acción");
        }else
        {
            return ic.proceed();
        }

    }

    boolean isAllowed(InvocationContext ic, UserDTO u) throws InternalServerErrorException {
        Method method = ic.getMethod();
        SecuredModel annotation = method.getAnnotation(SecuredModel.class);

        Object[] params = ic.getParameters();


        if (params == null || params.length < 1) {
            return false;
        }

        Long id = (Long) params[0];

        SecuredManageableTypeEnum[] securedManageableTypesArray = annotation.securedManageableTypes();
        List<SecuredManageableTypeEnum> securedManageableTypes =  Arrays.asList(securedManageableTypesArray);
        return secureManageableService.isAllowed(securedManageableTypes, id);
    }
}

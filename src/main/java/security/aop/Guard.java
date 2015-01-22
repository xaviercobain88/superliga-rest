package security.aop;

import core.domain.enums.SecuredManageableTypeEnum;
import security.domain.annotation.SecuredModel;
import security.application.contract.IModelAuthorizationService;
import security.domain.model.User;

import javax.ejb.EJB;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by xavier on 1/20/15.
 */
public class Guard {

    @EJB
    protected IModelAuthorizationService secureManageableService;
    @Inject
    Instance<User> users;

    @AroundInvoke
    public Object validatePermissions(InvocationContext ic) throws Exception {
        User user = users.get();
        if (!isAllowed(ic, user)) {
            throw new SecurityException("User " + user + " is not allowed to execute the method " );
        }
        return ic.proceed();
    }

    boolean isAllowed(InvocationContext ic, User u) {
        Method method = ic.getMethod();
        SecuredModel annotation = method.getAnnotation(SecuredModel.class);

        Object[] params = ic.getParameters();

        if (annotation == null) {
            return true;
        }
        if (params == null || params.length < 1) {
            return false;
        }

        Long id = (Long) params[0];

        SecuredManageableTypeEnum[] securedManageableTypesArray = annotation.securedManageableTypes();
        ArrayList<SecuredManageableTypeEnum> securedManageableTypes = (ArrayList<SecuredManageableTypeEnum>) Arrays.asList(securedManageableTypesArray);
        return secureManageableService.isAllowed(securedManageableTypes, id);
    }
}

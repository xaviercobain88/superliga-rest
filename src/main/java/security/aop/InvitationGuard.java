package security.aop;

import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import security.application.contract.IInvitationAuthorizationService;
import security.application.dto.UserDTO;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Created by xavier on 1/20/15.
 */
@SecuredInvitation
@Interceptor

public class InvitationGuard {

    @Inject
    protected IInvitationAuthorizationService invitationAuthorizationService;
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
        } else {
            return ic.proceed();
        }

    }

    boolean isAllowed(InvocationContext ic, @NotNull UserDTO u) throws InternalServerErrorException, BadRequestException {
        Method method = ic.getMethod();
        SecuredInvitation annotation = method.getAnnotation(SecuredInvitation.class);
        Object[] params = ic.getParameters();


        if (params == null || params.length < 1) {
            return false;
        }

        Long invitationId = (Long) params[0];
        Long userId = u.getId();
        return invitationAuthorizationService.isAllowed(invitationId, userId, annotation.extrict());
    }
}

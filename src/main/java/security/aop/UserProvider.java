package security.aop;



import core.application.contract.IUserService;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.application.exception.NotFoundException;
import core.domain.model.User;
import security.application.dto.UserDTO;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.security.Principal;

/**
 * Created by xavier on 1/20/15.
 */
public class UserProvider {
    @Inject
    Principal principal;
    @Inject
    protected IUserService userService;


    @Produces
    @LoggedUser
    public UserDTO fetch(){
        UserDTO userDTO = null;
        try {
            userDTO = userService.getByUsername("xaviercobain88");
        } catch (BadRequestException | InternalServerErrorException | NotFoundException e) {
            e.printStackTrace();
        }

        // user.setPermissions(realm.getPermissionForPrincipal(principal.getName()));
        return userDTO;
    }
}

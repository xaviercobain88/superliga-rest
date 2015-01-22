package security.aop;



import core.domain.model.User;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import java.security.Principal;

/**
 * Created by xavier on 1/20/15.
 */
public class UserProvider {
    @Inject
    Principal principal;


    @Produces
    @LoggedUser
    public User fetch(){
        User user = new User(principal.getName());
       // user.setPermissions(realm.getPermissionForPrincipal(principal.getName()));
        return user;
    }
}

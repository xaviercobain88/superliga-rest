package security.domain.impl;

import security.domain.enums.SecuredManageableTypeEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.contract.ITeamRepository;
import core.domain.contract.IUserRepository;
import core.domain.model.Team;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.contract.IModelAuthorizationHandler;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
@Stateless
public class ModelAuthorizationHandler implements IModelAuthorizationHandler {

    @Inject
    protected IUserRepository userRepository;
    @Inject
    protected ITeamRepository teamRepository;

    @Override
    public boolean isAllowed(List<SecuredManageableTypeEnum> securedManageableTypes, Long manageableResourceId, Long userId) throws UnexpectedPersistenceException, InvalidArgumentException {

        //TODO: verificar que el user que llega como parametro no se desatacha
        User userWithManageablePermissions = null;
        try {
            userWithManageablePermissions = userRepository.loadWithAllPermission(userId);
        } catch (DomainModelNotLoadedException e) {
            e.printStackTrace();
            return false;
        }

        if (securedManageableTypes.size() >= 1) {
            if (userWithManageablePermissions.isManager(manageableResourceId, securedManageableTypes.get(0))) {
                return true;
            }
            //TODO: Desarrollado solo para administrador de teams

            if (securedManageableTypes.contains(SecuredManageableTypeEnum.PLAYER) &&
                    securedManageableTypes.contains(SecuredManageableTypeEnum.TEAM)) {

                List<Team> teams = teamRepository.findByPlayerId(manageableResourceId);

                if (teams == null || teams.isEmpty()) {
                    return false;
                }
                for (Team team : teams) {
                    if (team != null) {
                        if (userWithManageablePermissions.isManager(team.getId(), SecuredManageableTypeEnum.TEAM)) {
                            return true;
                        }
                    }
                }

            }
        }

        return false;

    }
}

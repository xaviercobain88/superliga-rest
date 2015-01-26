package core.infrastructure.impl;

import core.domain.contract.IModelAdminRepository;
import core.domain.model.Player;
import core.domain.model.User;
import security.domain.enums.SecuredManageableTypeEnum;

import javax.ejb.Stateless;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * Created by xavier on 1/25/15.
 */
@Stateless
public class ModelAdminRepository extends GenericRepository<User, Long> implements IModelAdminRepository{
    public ModelAdminRepository() {
        super(User.class);
    }
    @Override
    public boolean create(@Min(1) Long userId, @Min(1) Long resourceId, @NotNull SecuredManageableTypeEnum securedManageableType) {
        String jpql = "INSERT INTO user_secure_manageable (secure_manageable_id, secure_manageable_type, user_id) " +
                "values (:resourceId, :securedManageableTypeString, :userId) ";
        HashMap<String, Object> params = new HashMap<>();
        String securedManageableTypeString = securedManageableType.toString();
        params.put("resourceId", resourceId);
        params.put("securedManageableTypeString", securedManageableTypeString);
        params.put("userId", userId);
        return executeUpdate(jpql, params);
    }
}

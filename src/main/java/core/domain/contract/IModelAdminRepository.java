package core.domain.contract;

import core.domain.model.User;
import security.domain.enums.SecuredManageableTypeEnum;

import javax.ejb.Local;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by xavier on 1/25/15.
 */
@Local
public interface IModelAdminRepository extends IGenericRepository<User, Long>{

    boolean create(@Min(1) Long userId, @Min(1)Long resourceId, @NotNull SecuredManageableTypeEnum securedManageableType);

}

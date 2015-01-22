package security.application.contract;

import core.domain.enums.SecuredManageableTypeEnum;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by xavier on 1/21/15.
 */
@Local
public interface IModelAuthorizationService {
    boolean isAllowed(List<SecuredManageableTypeEnum> securedManageableTypes, Long id);
}

package security.domain.contract;

import security.domain.enums.SecuredManageableTypeEnum;

/**
 * Created by xavier on 1/20/15.
 */
public interface SecuredManageable {

    Long getId();
    SecuredManageableTypeEnum getSecuredManageableType();
}

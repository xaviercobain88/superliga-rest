package core.domain.contract;

import core.domain.enums.SecuredManageableTypeEnum;

/**
 * Created by xavier on 1/20/15.
 */
public interface SecuredManageable {

    Long getId();
    SecuredManageableTypeEnum getSecuredManageableType();
}

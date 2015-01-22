package core.domain.model;

import core.domain.interfaces.SecuredManageable;
import core.domain.enums.SecuredManageableTypeEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by xavier on 1/20/15.
 */
@Entity
public class Tournament implements SecuredManageable {

    @Id
    @GeneratedValue
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public SecuredManageableTypeEnum getSecuredManageableType() {
        return SecuredManageableTypeEnum.TOURNAMENT;
    }
}

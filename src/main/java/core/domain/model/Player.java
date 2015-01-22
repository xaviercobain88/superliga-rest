package core.domain.model;

import core.domain.interfaces.SecuredManageable;
import core.domain.enums.SecuredManageableTypeEnum;

import javax.persistence.*;

/**
 * Created by xavier on 1/20/15.
 */
@Entity
public class Player implements SecuredManageable {

    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @Override
    public Long getId() {
        return id;
    }



    @Override
    public SecuredManageableTypeEnum getSecuredManageableType() {
        return SecuredManageableTypeEnum.PLAYER;
    }
}

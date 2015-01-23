package core.domain.model;

import core.domain.contract.SecuredManageable;
import core.domain.enums.SecuredManageableTypeEnum;

import javax.persistence.*;
import java.util.List;

/**
 * Created by xavier on 1/20/15.
 */
@Entity
public class Team implements SecuredManageable {

    @Id
    @GeneratedValue
    protected Long id;

    @OneToMany(mappedBy = "team")
    protected List<Player> players;

    @ManyToMany(mappedBy = "teams")
    protected List<Tournament> tournaments;

    @Override
    public SecuredManageableTypeEnum getSecuredManageableType() {
        return SecuredManageableTypeEnum.TEAM;
    }

    @Override
    public Long getId() {
        return id;
    }
}

package core.domain.model;

import core.domain.contract.SecuredManageable;
import core.domain.enums.SecuredManageableTypeEnum;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name="tournament_team",
            joinColumns=
            @JoinColumn(name="tournament_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="team_id", referencedColumnName="id")
    )
    protected List<Team> teams;

    @Override
    public SecuredManageableTypeEnum getSecuredManageableType() {
        return SecuredManageableTypeEnum.TOURNAMENT;
    }
}

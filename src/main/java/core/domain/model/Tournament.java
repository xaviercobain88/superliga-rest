package core.domain.model;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;
import security.domain.contract.SecuredManageable;
import security.domain.enums.SecuredManageableTypeEnum;

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
    protected String name;


    public Tournament(String name) {
        this.name = name;
    }

    public Tournament() {
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
    public Long getId() {
        return id;
    }

    @Override
    public SecuredManageableTypeEnum getSecuredManageableType() {
        return SecuredManageableTypeEnum.TOURNAMENT;
    }

    public String getName() {
        return name;
    }
}

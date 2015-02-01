package core.domain.model;

import security.domain.contract.SecuredManageable;
import security.domain.enums.SecuredManageableTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavier on 1/20/15.
 */
@Entity
public class Team implements SecuredManageable {

    @Id
    @GeneratedValue
    protected Long id;

    protected String name;

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

    public void addTournament(@NotNull Tournament tournament) {
        if (tournaments == null) {
            tournaments = new ArrayList<>();
        }
        tournaments.add(tournament);
    }

    public void setName(String name) {
        this.name = name;
    }
}

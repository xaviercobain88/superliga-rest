package core.domain.model;

import core.domain.enums.GenericStatusEnum;
import security.domain.contract.SecuredManageable;
import security.domain.enums.SecuredManageableTypeEnum;
import core.domain.enums.UserStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    protected User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotNull
    protected Team team;

    @Enumerated(EnumType.STRING)
    protected GenericStatusEnum status;

    @Override
    public Long getId() {
        return id;
    }


    public Player(Team team, User user) {
        this.team = team;
        this.user = user;
        status = GenericStatusEnum.ACTIVE;
    }

    @Override
    public SecuredManageableTypeEnum getSecuredManageableType() {
        return SecuredManageableTypeEnum.PLAYER;
    }


    public Long getTeamId(){
        if(team!=null){
            return team.getId();
        }
        return null;
    }



}

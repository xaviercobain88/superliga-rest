package core.domain.model;

import core.domain.contract.SecuredManageable;
import core.domain.enums.SecuredManageableTypeEnum;
import core.domain.enums.StatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "team_id")
    protected Team team;

    @Enumerated(EnumType.STRING)
    protected StatusEnum status;

    @Override
    public Long getId() {
        return id;
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

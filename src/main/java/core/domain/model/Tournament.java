package core.domain.model;

import core.application.enums.MessagesEnum;
import core.domain.enums.DisciplineEnum;
import core.domain.enums.GenericStatusEnum;
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
    @Enumerated(EnumType.STRING)
    protected DisciplineEnum discipline;
    protected Integer inputTeams;
    protected GenericStatusEnum status;



    public Tournament(String name, DisciplineEnum discipline) {
        this();
        this.name = name;
        this.discipline = discipline;


    }

    public Tournament() {
        this.status = GenericStatusEnum.INACTIVE;
        this.discipline = DisciplineEnum.FOOTBALL;
        this.name = MessagesEnum.NEW_TOURNAMENT_NAME.name();
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public SecuredManageableTypeEnum getSecuredManageableType() {
        return SecuredManageableTypeEnum.TOURNAMENT;
    }

    public String getName() {
        return name;
    }

    public DisciplineEnum getDiscipline() {
        return discipline;
    }

    public void setInputTeams(Integer inputTeams) {
        this.inputTeams = inputTeams;
    }

    public Integer getInputTeams() {
        return inputTeams;
    }

    public boolean isForTeam(){
        if(discipline!=null){
            return discipline.isForTeam();
        }
        return false;
    }

}

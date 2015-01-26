package core.application.dto;

import core.domain.enums.PlayingModeEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by xavier on 1/26/15.
 */
public class StageDTO {
    protected Long id;
    @NotNull
    protected PlayingModeEnum mode;
    @Min(2)
    protected Integer registeredTeams;
    @Min(1)
    protected Integer classifiedTeams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayingModeEnum getMode() {
        return mode;
    }

    public void setMode(PlayingModeEnum mode) {
        this.mode = mode;
    }

    public Integer getRegisteredTeams() {
        return registeredTeams;
    }

    public void setRegisteredTeams(Integer registeredTeams) {
        this.registeredTeams = registeredTeams;
    }

    public Integer getClassifiedTeams() {
        return classifiedTeams;
    }

    public void setClassifiedTeams(Integer classifiedTeams) {
        this.classifiedTeams = classifiedTeams;
    }
}

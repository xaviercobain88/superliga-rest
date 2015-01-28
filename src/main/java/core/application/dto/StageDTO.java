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
    protected Integer inputTeams;
    @Min(1)
    protected Integer outputTeams;
    protected Integer groupsNumber;

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

    public Integer getInputTeams() {
        return inputTeams;
    }

    public void setInputTeams(Integer inputTeams) {
        this.inputTeams = inputTeams;
    }

    public Integer getOutputTeams() {
        return outputTeams;
    }

    public void setOutputTeams(Integer outputTeams) {
        this.outputTeams = outputTeams;
    }

    public Integer getGroupsNumber() {
        return groupsNumber;
    }

    public void setGroupsNumber(Integer groupsNumber) {
        this.groupsNumber = groupsNumber;
    }
}

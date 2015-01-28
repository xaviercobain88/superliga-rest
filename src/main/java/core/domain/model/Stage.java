package core.domain.model;

import core.domain.enums.PlayingModeEnum;
import core.domain.exception.InvalidArgumentsForTournamentSetupException;

import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Entity
public class Stage {

    @Id
    @GeneratedValue
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "next_stage_id")
    protected Stage nextStage;
    @ManyToMany
    @JoinTable(name = "input_output_stage",
            joinColumns =
            @JoinColumn(name = "input_source_stage_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "output_target_stage_id", referencedColumnName = "id")
    )
    protected List<Stage> outputTargetStages;
    @ManyToMany(mappedBy = "outputTargetStages")
    protected List<Stage> inputSourceStages;
    @Min(2)
    protected Integer inputTeams;
    @Min(1)
    //Para groups funciona como el output del grupo
    protected Integer outputTeams;
    @Enumerated(EnumType.STRING)
    protected PlayingModeEnum mode;
    @OneToMany(mappedBy = "stage")
    protected List<Group> groups;
    protected Integer groupsNumber;
    @Inject
    @Transient
    protected Logger logger;

    public void createGroups() throws InvalidArgumentsForTournamentSetupException {
        groups = new ArrayList<>();
        if (inputTeams > outputTeams) {
            if (mode.equals(PlayingModeEnum.GROUPS)) {

                if (inputTeams <= groupsNumber ) {
                    throw new InvalidArgumentsForTournamentSetupException("Invalid arguments for input and output teams of GROUP mode stage");
                }

                Double singleGroupTeams = Math.ceil(inputTeams.doubleValue() / groupsNumber.doubleValue());
                for (int i = 1; i <= groupsNumber; i++) {
                    Group group = new Group();
                    group.setStage(this);
                    group.setOrder(i - 1);
                    if (groupsNumber.equals(i)) {
                        group.setInputTeams(inputTeams % (singleGroupTeams.intValue()));
                        if(group.getInputTeams().equals(0)){
                            group.setInputTeams(singleGroupTeams.intValue());
                        }
                    } else {
                        group.setInputTeams(singleGroupTeams.intValue());
                    }
                    group.setOutputTeams(outputTeams);
                    group.setMode(PlayingModeEnum.ROUND_ROBIN);
                    groups.add(group);

                }


            } else {

                this.groupsNumber = 1;
                Group group = new Group();
                group.setStage(this);
                group.setInputTeams(inputTeams);
                group.setOutputTeams(outputTeams);
                group.setMode(mode);
                groups.add(group);

            }

        }else{
            throw new InvalidArgumentsForTournamentSetupException("Invalid arguments for input and output teams");
        }


    }

    public boolean isValid() {
        Double inputLogBase2 = Math.log(outputTeams) / Math.log(2);
        Double inputCheck = inputLogBase2 - Math.ceil(inputLogBase2);
        Double outputLogBase2 = Math.log(inputTeams) / Math.log(2);
        Double outputCheck = inputLogBase2 - Math.ceil(outputLogBase2);
        return inputTeams > outputTeams && inputTeams > groupsNumber &&
                (!mode.equals(PlayingModeEnum.SINGLE_ELIMINATION) || (inputCheck.equals(0d) && outputCheck.equals(0d)));
    }

    private Integer getTotalTeamsOfGroups(){
        Integer total = 0;
        if(groups!=null){
            for (Group group:groups){
                if(group!=null && group.getInputTeams()>0){
                    total+=group.getInputTeams();
                }
            }
            return total;
        }else{
            return 0;
        }
    }

    public void setNextStage(Stage nextStage) {
        this.nextStage = nextStage;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Integer getOutputTeams() {
        return outputTeams;
    }

    public Integer getInputTeams() {
        return inputTeams;
    }

    public Long getId() {
        return id;
    }

    public void setInputSourceStages(List<Stage> inputSourceStages) {
        this.inputSourceStages = inputSourceStages;
    }

    public void addOutputTargetStage(Stage stage) {
        if(outputTargetStages==null){
            outputTargetStages = new ArrayList<>();
        }
        outputTargetStages.add(stage);
    }

    public void setDefaultGroupsNumber(){
        if(groupsNumber==null || groupsNumber==0){
            groupsNumber=1;
        }
    }

    public void setInputTeams(Integer inputTeams) {
        this.inputTeams = inputTeams;
    }

    public void setOutputTeams(Integer outputTeams) {
        this.outputTeams = outputTeams;
    }

    public PlayingModeEnum getMode() {
        return mode;
    }

    public void setMode(PlayingModeEnum mode) {
        this.mode = mode;
    }

    public Integer getGroupsNumber() {
        return groupsNumber;
    }

    public void setGroupsNumber(Integer groupsNumber) {
        this.groupsNumber = groupsNumber;
    }
}

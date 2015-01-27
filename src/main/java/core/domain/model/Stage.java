package core.domain.model;

import core.domain.enums.PlayingModeEnum;
import core.domain.exception.InvalidArgumentsForTournamentSetupException;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

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
    protected Integer ouputTeams;
    @Enumerated(EnumType.STRING)
    protected PlayingModeEnum mode;
    @OneToMany(mappedBy = "stage")
    protected List<Group> groups;
    protected Integer groupsNumber;

    public void createGroups() throws InvalidArgumentsForTournamentSetupException {
        groups = new ArrayList<>();
        if (inputTeams > ouputTeams) {
            if (mode.equals(PlayingModeEnum.GROUPS)) {

                if (inputTeams <= groupsNumber || (groupsNumber * ouputTeams > inputTeams)) {
                    throw new InvalidArgumentsForTournamentSetupException("Invalid arguments for input and output teams of GROUP mode stage");
                }

                Double singleGroupTeams = Math.ceil(inputTeams / groupsNumber);
                for (int i = 1; i <= groupsNumber; i++) {
                    Group group = new Group();
                    group.setStage(this);
                    group.setOrder(i - 1);
                    if (groupsNumber.equals(i)) {
                        group.setInputTeams(inputTeams % (singleGroupTeams.intValue()));
                    } else {
                        group.setInputTeams(singleGroupTeams.intValue());
                    }
                    group.setOuputTeams(ouputTeams);
                    group.setMode(PlayingModeEnum.ROUND_ROBIN);
                    groups.add(group);

                }


            } else {

                this.groupsNumber = 1;
                Group group = new Group();
                group.setStage(this);
                group.setInputTeams(inputTeams);
                group.setOuputTeams(ouputTeams);
                group.setMode(mode);
                groups.add(group);

            }

        }
        throw new InvalidArgumentsForTournamentSetupException("Invalid arguments for input and output teams");

    }

    public boolean isValid() {
        Double inputLogBase2=Math.log(ouputTeams)/Math.log(2);
        Double inputCheck = inputLogBase2 - Math.ceil(inputLogBase2);
        Double outputLogBase2=Math.log(inputTeams)/Math.log(2);
        Double outputCheck = inputLogBase2 - Math.ceil(outputLogBase2);
        return inputTeams > ouputTeams && inputTeams > groupsNumber && (groupsNumber * ouputTeams < inputTeams) &&
                (!mode.equals(PlayingModeEnum.SINGLE_ELIMINATION) || (inputCheck.equals(0d)&&outputCheck.equals(0d)));
    }

    public void setNextStage(Stage nextStage) {
        this.nextStage = nextStage;
    }

    public List<Group> getGroups() {
        return groups;
    }
}

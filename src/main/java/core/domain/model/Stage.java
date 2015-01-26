package core.domain.model;

import javax.persistence.*;
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
	@JoinTable(name="input_output_stage",
			joinColumns=
			@JoinColumn(name="input_source_stage_id", referencedColumnName="id"),
			inverseJoinColumns=
			@JoinColumn(name="output_target_stage_id", referencedColumnName="id")
	)
	protected List<Stage> outputTargetStages;
	@ManyToMany(mappedBy = "outputTargetStages")
	protected List<Stage> inputSourceStages;
	@Transient
	protected List<Group> groups;
	
	
	
}

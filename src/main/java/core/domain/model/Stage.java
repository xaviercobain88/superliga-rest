package core.domain.model;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

public class Stage {

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
	protected List<Group> groups;
	
	
	
}

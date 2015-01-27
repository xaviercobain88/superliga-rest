package core.domain.model;

import core.domain.enums.PlayingModeEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Group {

	@Id
	@GeneratedValue
	protected Long id;
	protected Integer order;
	@Enumerated(EnumType.STRING)
	protected PlayingModeEnum mode;
	@Min(2)
	protected Integer inputTeams;
	@Min(1)
	protected Integer ouputTeams;
	@ManyToOne
	@JoinColumn(name = "stage_id")
	protected Stage stage;


	public Group() {
		order=0;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public void setInputTeams(Integer inputTeams) {
		this.inputTeams = inputTeams;
	}

	public void setOuputTeams(Integer ouputTeams) {
		this.ouputTeams = ouputTeams;
	}

	public void setMode(PlayingModeEnum mode) {
		this.mode = mode;
	}
}

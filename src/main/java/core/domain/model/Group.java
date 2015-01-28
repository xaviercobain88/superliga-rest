package core.domain.model;

import core.domain.enums.PlayingModeEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity(name = "stage_group")
public class Group {

	@Id
	@GeneratedValue
	protected Long id;
	@Column(name = "group_order")
	protected Integer order;
	@Enumerated(EnumType.STRING)
	@Column(name = "group_mode")
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

	public void setOutputTeams(Integer outputTeams) {
		this.ouputTeams = ouputTeams;
	}

	public void setMode(PlayingModeEnum mode) {
		this.mode = mode;
	}

	public Integer getInputTeams() {
		return inputTeams;
	}
}

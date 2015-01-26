package core.domain.model;

import core.domain.enums.PlayingModeEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Group {

	@Id
	@GeneratedValue
	protected Long id;
	@Transient
	protected Integer order;
	@Transient
	protected PlayingModeEnum tournamentMode;
	@Transient
	protected Integer clasifiedTeams;
	@Transient
	protected Integer registerdTeams;
	
	

}

package xaw.rest_services.application.dto;

import xaw.rest_services.domain.enums.IOGroupStatusEnum;
import xaw.rest_services.domain.enums.TournamentModeEnum;

public class GroupDTO {

	protected TournamentModeEnum mode;
	protected Integer registeredTeams;
	protected Integer clasifiedoutTeams;
	protected Boolean doubleRound;
	protected Integer stage;
	protected Integer order;
	protected IOGroupStatusEnum inputStatus;
	protected IOGroupStatusEnum outputStatus;
	public TournamentModeEnum getMode() {
		return mode;
	}
	public void setMode(TournamentModeEnum mode) {
		this.mode = mode;
	}
	public Integer getRegisteredTeams() {
		return registeredTeams;
	}
	public void setRegisteredTeams(Integer registeredTeams) {
		this.registeredTeams = registeredTeams;
	}
	public Integer getClasifiedoutTeams() {
		return clasifiedoutTeams;
	}
	public void setClasifiedoutTeams(Integer clasifiedoutTeams) {
		this.clasifiedoutTeams = clasifiedoutTeams;
	}
	public Boolean getDoubleRound() {
		return doubleRound;
	}
	public void setDoubleRound(Boolean doubleRound) {
		this.doubleRound = doubleRound;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public IOGroupStatusEnum getInputStatus() {
		return inputStatus;
	}
	public void setInputStatus(IOGroupStatusEnum inputStatus) {
		this.inputStatus = inputStatus;
	}
	public IOGroupStatusEnum getOutputStatus() {
		return outputStatus;
	}
	public void setOutputStatus(IOGroupStatusEnum outputStatus) {
		this.outputStatus = outputStatus;
	}
	
	

}

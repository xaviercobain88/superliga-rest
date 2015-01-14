package xaw.rest_services.application.dto;

import java.util.ArrayList;

public class TournamentSettingsDTO {

	protected String name;
	protected Long disciplineId;
	protected String imageUrl;
	protected ArrayList<String> invitations;
	protected ArrayList<GroupDTO> groups;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(Long disciplineId) {
		this.disciplineId = disciplineId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ArrayList<String> getInvitations() {
		return invitations;
	}

	public void setInvitations(ArrayList<String> invitations) {
		this.invitations = invitations;
	}

	public ArrayList<GroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<GroupDTO> groups) {
		this.groups = groups;
	}

}

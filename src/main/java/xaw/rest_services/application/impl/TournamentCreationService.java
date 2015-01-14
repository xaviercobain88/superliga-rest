package xaw.rest_services.application.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import xaw.rest_services.application.contract.ITournamentCreationService;
import xaw.rest_services.application.dto.TournamentSettingsDTO;
import xaw.rest_services.application.dto.TournamentDTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TournamentCreationService implements ITournamentCreationService {

	@Override
	public TournamentDTO createTournament(
			TournamentSettingsDTO tournamentSettingsDTO) {
		return null;
	}

}

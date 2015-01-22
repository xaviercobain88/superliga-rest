package core.application.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import core.application.contract.ITournamentService;
import core.application.dto.TournamentDTO;
import core.application.dto.TournamentSettingsDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TournamentService implements ITournamentService {

	@Override
	public TournamentDTO createTournament(
			TournamentSettingsDTO tournamentSettingsDTO)
			throws BadRequestException, InternalServerErrorException {
		
		
		
		return null;
	}

	

}

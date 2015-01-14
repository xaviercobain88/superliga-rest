package xaw.rest_services.application.contract;

import xaw.rest_services.application.dto.TournamentSettingsDTO;
import xaw.rest_services.application.dto.TournamentDTO;
import xaw.rest_services.application.exception.BadRequestException;
import xaw.rest_services.application.exception.InternalServerErrorException;

public interface ITournamentCreationService {

	TournamentDTO createTournament(
			TournamentSettingsDTO tournamentSettingsDTO) throws BadRequestException, InternalServerErrorException;

}

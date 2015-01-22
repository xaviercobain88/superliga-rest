package core.application.contract;

import core.application.dto.TournamentDTO;
import core.application.dto.TournamentSettingsDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;

public interface ITournamentService {

	TournamentDTO createTournament(
			TournamentSettingsDTO tournamentSettingsDTO) throws BadRequestException, InternalServerErrorException;

}

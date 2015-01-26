package core.application.contract;

import core.application.dto.TournamentDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.application.exception.NotFoundException;
import core.application.exception.UnauthorizedException;
import security.application.dto.UserDTO;

import javax.ejb.Local;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Local
public interface ITournamentService {
	TournamentDTO create(@NotNull @Min(1) Long userId,  @Valid TournamentDTO tournamentDTO) throws InternalServerErrorException, UnauthorizedException;
}

package core.application.contract;

import core.application.dto.StageDTO;
import core.application.dto.TournamentDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import core.domain.model.User;
import org.hibernate.validator.constraints.NotEmpty;
import security.application.dto.UserDTO;

import javax.ejb.Local;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Local
public interface ITournamentService {
    TournamentDTO create(@NotNull @Min(1) Long userId, @Valid TournamentDTO tournamentDTO) throws InternalServerErrorException, UnauthorizedException;

    List<StageDTO> setStages(@Min(1) Long tournamentId, @Valid @NotEmpty List<StageDTO> stageDTOs) throws InternalServerErrorException, BadRequestException;

    List<UserDTO> sendInvitations(@Min(1) Long tournamentId, @NotEmpty Set<String> emails, @NotNull UserDTO sender) throws InternalServerErrorException;
}

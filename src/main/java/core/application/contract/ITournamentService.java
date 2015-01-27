package core.application.contract;

import core.application.dto.StageDTO;
import core.application.dto.TournamentDTO;
import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Local;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Local
public interface ITournamentService {
    TournamentDTO create(@NotNull @Min(1) Long userId, @Valid TournamentDTO tournamentDTO) throws InternalServerErrorException, UnauthorizedException;

    StageDTO setStages(@Min(1) Long tournamentId, @Valid @NotEmpty List<StageDTO> stageDTOs);
}

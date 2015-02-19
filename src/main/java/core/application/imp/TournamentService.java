package core.application.imp;

import core.application.contract.ITournamentService;
import core.application.dto.StageDTO;
import core.application.dto.TournamentDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import core.domain.contract.ITournamentHandler;
import core.domain.contract.ITournamentRepository;
import core.domain.contract.IUserRepository;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.exception.InvalidArgumentsForTournamentSetupException;
import core.domain.exception.InvalidInvitationException;
import core.domain.model.Stage;
import core.domain.model.Tournament;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.validator.constraints.NotEmpty;
import security.aop.SecuredModel;
import security.application.dto.UserDTO;
import security.domain.enums.SecuredManageableTypeEnum;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by xavier on 1/24/15.
 */
@Stateless

public class TournamentService implements ITournamentService {

    @Inject
    protected ITournamentHandler tournamentHandler;
    @Inject
    protected IUserRepository userRepository;
    @Inject
    protected ITournamentRepository tournamentRepository;


    @Override
    @SecuredModel(securedManageableTypes = SecuredManageableTypeEnum.TOURNAMENT)

    public TournamentDTO update(@NotNull @Min(1) Long tournamentId, @Valid TournamentDTO tournamentDTO) throws InternalServerErrorException, UnauthorizedException {
        Tournament tournament = new Tournament(tournamentDTO.getName(), tournamentDTO.getDiscipline());

        try {
            tournament.setId(tournamentId);
            tournamentHandler.update(tournament);
            TournamentDTO newTournamentDTO = new TournamentDTO();
            BeanUtils.copyProperties(newTournamentDTO, tournament);
            return newTournamentDTO;
        } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }

    @Override
    public List<StageDTO> setStages(@Min(1) Long tournamentId, @Valid @NotEmpty List<StageDTO> stageDTOs) throws InternalServerErrorException, BadRequestException {
        List<Stage> stages = new ArrayList<>();

        try {
            for (StageDTO stageDTO : stageDTOs) {
                Stage stage = new Stage();
                BeanUtils.copyProperties(stage, stageDTO);
                stage.setDefaultGroupsNumber();
                stages.add(stage);

            }
            tournamentHandler.setStages(tournamentId, stages);
        } catch (InvocationTargetException | IllegalAccessException | UnexpectedPersistenceException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        } catch (InvalidArgumentsForTournamentSetupException | DomainModelNotLoadedException e) {
            e.printStackTrace();
            throw new BadRequestException("No has enviado una configuración válida");
        }
        return stageDTOs;

    }

    @Override
    public List<StageDTO> getStages(@Min(1) Long tournamentId) {
        return new ArrayList<StageDTO>();
    }

    @Override
    public List<UserDTO> sendInvitations(@Min(1) Long tournamentId, @NotEmpty Set<String> emails, @NotNull UserDTO senderDTO) throws InternalServerErrorException, BadRequestException {
        List<UserDTO> invitedUserDTOs = new ArrayList<>();
        try {
            User sender = userRepository.load(senderDTO.getId());
            List<User> invitedUsers = tournamentHandler.sendInvitations(tournamentId, emails, sender);
            for (User user : invitedUsers) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername(user.getUsername());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                invitedUserDTOs.add(userDTO);
            }
        } catch (UnexpectedPersistenceException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        } catch (DomainModelNotLoadedException e) {
            e.printStackTrace();
            throw new BadRequestException("No has enviado una configuración válida");
        } catch (InvalidInvitationException e) {
            e.printStackTrace();
            throw new BadRequestException("La cantidad de invitaciones ha excedido el número de equipos");
        }
        return invitedUserDTOs;
    }

    @Override
    @SecuredModel(securedManageableTypes = SecuredManageableTypeEnum.USER)
    public TournamentDTO createEmpty(@NotNull @Min(1) Long userId) throws InternalServerErrorException , UnauthorizedException{
        Tournament tournament = new Tournament();

        try {
            tournamentHandler.create(userId, tournament);
            TournamentDTO newTournamentDTO = new TournamentDTO();
            BeanUtils.copyProperties(newTournamentDTO, tournament);
            return newTournamentDTO;
        } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }

    @Override
    public TournamentDTO getById(@NotNull @Min(1) Long tournamentId) throws InternalServerErrorException {
        try {
            Tournament tournament=tournamentRepository.load(tournamentId);
            TournamentDTO tournamentDTO = new TournamentDTO();
            BeanUtils.copyProperties(tournamentDTO, tournament);
            return tournamentDTO;
        } catch (UnexpectedPersistenceException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }
}

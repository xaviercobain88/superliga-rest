package core.domain.contract;

import core.domain.enums.InvitationStatusEnum;
import core.domain.enums.InvitationTypeEnum;
import core.domain.model.Invitation;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Local;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Local
public interface IInvitationRepository extends IGenericRepository<Invitation, Long> {


    //TODO: Cambiar por un count
    List<Invitation> findByResource(@Min(1) Long polymorphicResourceId, @NotNull InvitationTypeEnum type, @NotEmpty List<InvitationStatusEnum> statuses)
            throws UnexpectedPersistenceException;
    List<Invitation> findByResourceAndEmail(@Min(1) Long polymorphicResourceId, @NotNull InvitationTypeEnum type, @NotEmpty List<InvitationStatusEnum> statuses,
                                            @Min(1) Long userId)
            throws UnexpectedPersistenceException;

}

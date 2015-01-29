package core.domain.contract;

import core.domain.model.Invitation;
import core.domain.model.Team;
import core.infrastructure.exception.UnexpectedPersistenceException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IInvitationRepository extends IGenericRepository<Invitation, Long> {


}

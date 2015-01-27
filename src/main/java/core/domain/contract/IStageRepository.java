package core.domain.contract;

import core.domain.model.Stage;
import core.domain.model.Team;
import core.infrastructure.exception.UnexpectedPersistenceException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IStageRepository extends IGenericRepository<Stage, Long> {


}

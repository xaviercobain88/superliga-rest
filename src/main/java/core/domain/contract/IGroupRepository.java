package core.domain.contract;

import core.domain.model.Group;
import core.domain.model.Stage;

import javax.ejb.Local;

@Local
public interface IGroupRepository extends IGenericRepository<Group, Long> {


}

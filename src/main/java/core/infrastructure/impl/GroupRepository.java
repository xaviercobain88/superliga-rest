package core.infrastructure.impl;

import core.domain.contract.IGroupRepository;
import core.domain.contract.IStageRepository;
import core.domain.model.Group;
import core.domain.model.Stage;

import javax.ejb.Stateless;

@Stateless
public class GroupRepository extends GenericRepository<Group, Long> implements
        IGroupRepository {

    public GroupRepository() {
        super(Group.class);
    }
}

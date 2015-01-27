package core.infrastructure.impl;

import core.domain.contract.IStageRepository;
import core.domain.model.Stage;

import javax.ejb.Stateless;

@Stateless
public class StageRepository extends GenericRepository<Stage, Long> implements
        IStageRepository {

    public StageRepository() {
        super(Stage.class);
    }
}

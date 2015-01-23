package security.infrastructure.impl;

import core.domain.infrastructure_service.ITeamRepository;
import core.domain.model.Team;
import core.infrastructure.impl.GenericRepository;
import security.domain.contract.IDistributedServiceTokenRepository;
import security.domain.model.DistributedServiceToken;

/**
 * Created by xavier on 1/23/15.
 */
public class DistributedServiceTokenRepository  extends GenericRepository<DistributedServiceToken, String> implements
        IDistributedServiceTokenRepository {
    public DistributedServiceTokenRepository() {
        super(DistributedServiceToken.class);
    }
}

package security.domain.contract;

import core.domain.contract.IGenericRepository;
import security.domain.model.DistributedServiceToken;

import javax.ejb.Local;

/**
 * Created by xavier on 1/23/15.
 */
@Local
public interface IDistributedServiceTokenRepository  extends IGenericRepository<DistributedServiceToken, String> {

}

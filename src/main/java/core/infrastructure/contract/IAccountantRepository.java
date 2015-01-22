package core.infrastructure.contract;

import javax.ejb.Local;

import core.domain.infrastructure_service.IGenericRepository;
import core.domain.model.Accountant;
import core.infrastructure.query_param.AccountantQueryParam;

@Local
public interface IAccountantRepository extends
		IGenericRepository<Accountant, AccountantQueryParam> {

}

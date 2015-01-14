package xaw.rest_services.infrastructure.contract;

import javax.ejb.Local;

import xaw.rest_services.domain.infrastructure_service.IGenericRepository;
import xaw.rest_services.domain.model.Accountant;
import xaw.rest_services.infrastructure.query_param.AccountantQueryParam;

@Local
public interface IAccountantRepository extends
		IGenericRepository<Accountant, AccountantQueryParam> {

}

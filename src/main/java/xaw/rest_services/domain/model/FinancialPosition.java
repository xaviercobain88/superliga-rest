package xaw.rest_services.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author xavier
 * 
 */
@Entity
public class FinancialPosition {
	@Id
	@GeneratedValue
	protected Long id;

	@ManyToOne
	@JoinColumn(name = "period_id")
	protected Period period;
	@ManyToOne
	@JoinColumn(name = "company_id")
	protected Company company;

}

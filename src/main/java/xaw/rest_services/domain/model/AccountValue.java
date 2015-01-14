package xaw.rest_services.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AccountValue {
	@Id
	@GeneratedValue
	protected Long id;
	protected BigDecimal value;
	@ManyToOne
	@JoinColumn(name = "finantial_position_id")
	protected FinancialPosition finantialPosition;

}

package xaw.rest_services.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import xaw.rest_services.domain.enums.StatusEnum;

@Entity
public class Period {

	@Id
	@GeneratedValue
	protected Long id;
	protected StatusEnum status;
	protected Boolean current;

}

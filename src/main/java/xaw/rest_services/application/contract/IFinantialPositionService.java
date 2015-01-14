package xaw.rest_services.application.contract;

import java.math.BigDecimal;
import java.util.ArrayList;

import xaw.rest_services.application.dto.AccountDTO;
import xaw.rest_services.application.exception.BadRequestException;
import xaw.rest_services.application.exception.InternalServerErrorException;

public interface IFinantialPositionService {

	ArrayList<AccountDTO> getStructure() throws InternalServerErrorException;

	ArrayList<AccountDTO> saveAccountValue(Long id, BigDecimal value,
			Long period)
			throws BadRequestException, InternalServerErrorException;
}

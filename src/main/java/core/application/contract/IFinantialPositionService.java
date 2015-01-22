package core.application.contract;

import java.math.BigDecimal;
import java.util.ArrayList;

import core.application.dto.AccountDTO;
import core.application.exception.BadRequestException;
import core.application.exception.InternalServerErrorException;

public interface IFinantialPositionService {

	ArrayList<AccountDTO> getStructure() throws InternalServerErrorException;

	ArrayList<AccountDTO> saveAccountValue(Long id, BigDecimal value,
			Long period)
			throws BadRequestException, InternalServerErrorException;
}

package utils;

import java.util.List;

import utils.exception.InvalidArgumentException;

public class ListUtils {

	public static Object getFirstItem(List<?> list) throws InvalidArgumentException {
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			throw new InvalidArgumentException();
		}
	}
}

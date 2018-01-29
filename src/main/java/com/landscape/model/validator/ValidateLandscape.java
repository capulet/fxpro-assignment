package com.landscape.model.validator;

import static com.landscape.model.LandscapConstants.MAX_HEIGHT;
import static com.landscape.model.LandscapConstants.MAX_NO_OF_POSITION;
import static com.landscape.model.LandscapConstants.MIN_HEIGHT;
import static com.landscape.model.LandscapConstants.ZERO;
import java.util.EnumMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.landscape.model.exception.InvalidDataExcecption;
import com.landscape.model.exception.InvalidPositionException;
import com.landscape.model.exception.OverflowException;
import com.landscape.model.helper.LandscapeHelper;
import com.landscape.model.type.LandForms;

public class ValidateLandscape {

  final static Logger logger = LoggerFactory.getLogger(ValidateLandscape.class);

  public static void checkNull(Object data) {
    if (data == null)
      throw new NullPointerException();
  }

  public static void checkPosition(Map<Integer, EnumMap<LandForms, Integer>> data, Integer position)
      throws InvalidPositionException {

    logger.debug("Inside checkPosition with params Data" + data + " \n position: " + position);
    if (!data.containsKey(position)) {
      logger.error("Invalid position Exception occured " + position);
      throw new InvalidPositionException("Position " + position
          + " do not exist in data, kindly make sure it is created before accessing it");
    }
  }

  public static boolean isValidData(Map<Integer, EnumMap<LandForms, Integer>> data,
      Integer position, Integer value, boolean hillFlag)
      throws InvalidPositionException, OverflowException, InvalidDataExcecption {
    logger.debug("Inside isValidData with params Data" + data + " \n position: " + position
        + " value: " + value + " HillFlag: " + hillFlag);
    if (hillFlag) {
      return validateLandscape(value, MIN_HEIGHT) ? true : false;
    }
    // during case of filling water inside pits
    else {
      if (!validateLandscape(value, ZERO)) {
        return false;
      }

      int remainingCapacity = LandscapeHelper.getMaximumWaterCanBeFilled(data, position);
      if (remainingCapacity >= value) {
        return true;
      } else {
        logger.error(
            "Overflow exception occured for position: " + position + " with value: " + value);
        throw new OverflowException("Water exceeds than capacity");
      }
    }
  }

  public static void validateData(Map<Integer, EnumMap<LandForms, Integer>> data, int position,
      Integer value, boolean hillFlag)
      throws InvalidPositionException, InvalidDataExcecption, OverflowException {
    logger.debug("Inside validateData with params Data" + data + " \n position: " + position
        + " value: " + value + " HillFlag: " + hillFlag);
    if (!isValidData(data, position, value, hillFlag)) {
      logger.error("Invalid Data Exception Occured: Position: " + position + " Value: " + value
          + " HillFlag" + hillFlag);
      throw new InvalidDataExcecption("Invalid data entered into Landscape");
    }
  }

  public static boolean validateLandscape(int hills, int pits) {
    return (hills >= MIN_HEIGHT && pits >= MIN_HEIGHT) ? (hills + pits <= MAX_HEIGHT) ? true : false
        : false;
  }

  public static boolean validateLandscape(Integer position, Integer hills, Integer pits) {
    if (position >= MAX_NO_OF_POSITION) // position start from 0 to MAX_NO_OF_POSITION - 1
      return false;
    return validateLandscape(hills, pits);
  }
}

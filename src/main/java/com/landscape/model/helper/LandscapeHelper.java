package com.landscape.model.helper;

import java.util.EnumMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.landscape.model.LandscapConstants;
import com.landscape.model.exception.InvalidPositionException;
import com.landscape.model.type.LandForms;
import com.landscape.model.validator.ValidateLandscape;

public class LandscapeHelper {

  final static Logger logger = LoggerFactory.getLogger(ValidateLandscape.class);

  /**
   * This fills pit with water till specified maxWater parameter, if cannot fill then returns Tuple
   * with remaining block same as value specified in argument and success status false.
   * 
   * @param position position of the Landform
   * @param value - available value of number of squares of water remaining as of state
   * @param maxWater - max height calculated of the pit, can accumulate water inside it
   * @return Tuple with Integer remaining waterBlock to be filled, success of current fill
   * @throws InvalidPositionException
   */
  public static boolean fillPit(Map<Integer, EnumMap<LandForms, Integer>> data, Integer position,
      int value) throws InvalidPositionException {
    logger.debug("Inside fillPit with params data: " + data + " \n position: " + position
        + " value: " + value);
    ValidateLandscape.checkNull(data);

    int availableCapacity = getMaximumWaterCanBeFilled(data, position);
    logger.debug("availableCapacity: " + availableCapacity);
    if (availableCapacity == 0) {
      return false;
    }
    boolean canFill = (value - availableCapacity <= 0) ? true : false;
    logger.debug("Can Fill: " + canFill);
    if (canFill) {
      data.get(position).put(LandForms.PITS, (value + getCurrentPit(data, position)));
    }

    return canFill;
  }

  public static int getCurrentLevel(Map<Integer, EnumMap<LandForms, Integer>> data,
      Integer position) throws InvalidPositionException {
    ValidateLandscape.checkNull(data);
    ValidateLandscape.checkPosition(data, position);

    return (data.get(position).get(LandForms.HILLS).intValue()
        + data.get(position).get(LandForms.PITS).intValue());
  }

  private static int getCurrentPit(Map<Integer, EnumMap<LandForms, Integer>> data,
      Integer position) {
    return data.get(position).get(LandForms.PITS);
  }

  public static int getMaximumWaterCanBeFilled(Map<Integer, EnumMap<LandForms, Integer>> data,
      Integer position) throws InvalidPositionException {
    ValidateLandscape.checkNull(data);
    logger.debug("Inside getMaximumWaterCanBeFilled witth params data: " + data + " \n position: "
        + position);
    int currentHeight = getCurrentLevel(data, position);

    logger.debug("Current Height: " + currentHeight);
    return LandscapConstants.MAX_HEIGHT - currentHeight >= 0
        ? LandscapConstants.MAX_HEIGHT - currentHeight
        : 0;
  }

}

package com.landscape.model;

import static com.landscape.model.LandscapConstants.HILL_FLAG;
import static com.landscape.model.LandscapConstants.PIT_FLAG;
import static com.landscape.model.LandscapConstants.ZERO;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.landscape.model.exception.InvalidDataExcecption;
import com.landscape.model.exception.InvalidPositionException;
import com.landscape.model.exception.OverflowException;
import com.landscape.model.helper.LandscapeHelper;
import com.landscape.model.type.LandForms;
import com.landscape.model.validator.ValidateLandscape;

public class Landscape {

  private Map<Integer, EnumMap<LandForms, Integer>> data;

  final Logger logger = LoggerFactory.getLogger(this.getClass());

  public Landscape() {
    data = new HashMap<>();
    logger.debug("Landscape Initialisation complete");
  }

  /**
   * Compute available water inside pits
   * 
   * @return number of blocks of pits filled with water
   */
  public long calculateDepositedWater() {
    return data.values().stream().collect(Collectors.toList()).stream()
        .collect(Collectors.summarizingLong(landform -> landform.get(LandForms.PITS).intValue()))
        .getSum();
  }

  private EnumMap<LandForms, Integer> createLandForm(Integer hills) {
    EnumMap<LandForms, Integer> landform = new EnumMap<>(LandForms.class);
    landform.put(LandForms.HILLS, hills);
    landform.put(LandForms.PITS, ZERO);

    logger.debug("Landform creation done: " + landform);
    return landform;
  }

  /**
   * <p>
   * <b>Insert</b> or <b>Edit</b> Hill or Pit at particular position
   * </p>
   * 
   * @param position -> position at which we wanted to insert
   * @param value -> height of the hill or pit
   * @param hillFlag -> indicates whether hill or pit
   * @throws InvalidDataExcecption when valid data is not inserted or edited at a position
   * @throws InvalidPositionException
   * @throws OverflowException
   */
  public void createPosition(Integer position, Integer value)
      throws InvalidDataExcecption, InvalidPositionException, OverflowException {
    ValidateLandscape.validateData(data, position, value, HILL_FLAG);
    data.put(position, createLandForm(value));
    logger.debug("Landscape position created: " + position);
  }

  /**
   * Fill the water inside the pits
   * 
   * @param value - number of squares
   * @return - status <b> true </b> or <b> false </b>
   * @throws InvalidPositionException
   * @throws OverflowException when water is more than available squares of pits
   */
  public void fillWater(int position, Integer value)
      throws InvalidDataExcecption, InvalidPositionException, OverflowException {
    logger.debug("Trying to fill water " + value + "at : " + position);

    ValidateLandscape.validateData(data, position, value, PIT_FLAG);
    LandscapeHelper.fillPit(data, position, value);

    logger.debug("Water filled: " + value + "at : " + position);
  }

}

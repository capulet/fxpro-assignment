package com.landscape;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.landscape.model.Landscape;
import com.landscape.model.exception.InvalidDataExcecption;
import com.landscape.model.exception.InvalidPositionException;
import com.landscape.model.exception.OverflowException;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args)
      throws InvalidDataExcecption, OverflowException, InvalidPositionException {
    final Logger logger = LoggerFactory.getLogger(App.class);

    logger.debug("Application Started");

    Landscape landscapeObject = new Landscape();
    landscapeObject.createPosition(0, 100);
    landscapeObject.createPosition(1, 3100);
    landscapeObject.createPosition(2, 1000);
    landscapeObject.createPosition(3, 900);

    landscapeObject.fillWater(0, 2300);
    landscapeObject.fillWater(2, 100);
    landscapeObject.fillWater(3, 2300);
    landscapeObject.fillWater(1, 100);

    logger.debug("Deposited Water: " + landscapeObject.calculateDepositedWater());
    logger.debug("Application Completed");
  }
}

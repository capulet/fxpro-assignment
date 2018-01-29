package com.landscape.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.lang.ref.WeakReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.landscape.TestSuit;
import com.landscape.model.exception.InvalidDataExcecption;
import com.landscape.model.exception.InvalidPositionException;
import com.landscape.model.exception.OverflowException;

public class LandscapeTest extends TestSuit {

  Landscape landscapeObject = null;
  WeakReference<Landscape> refLandscapeObj = null;

  @Before
  public void setup() {
    refLandscapeObj = new WeakReference<>(new Landscape());
    landscapeObject = refLandscapeObj.get();
  }

  @After
  public void tearDown() {
    refLandscapeObj.clear();
  }

  @Test(expected = InvalidDataExcecption.class)
  public void test_InValid_Hill_Insertion()
      throws InvalidDataExcecption, InvalidPositionException, OverflowException {

    // instantiating position with invalid values for hills
    landscapeObject.createPosition(0, -100);
    landscapeObject.createPosition(1, 32200);
    landscapeObject.createPosition(2, 1055500);
    landscapeObject.createPosition(3, 9000);
  }

  @Test(expected = OverflowException.class)
  public void test_InValid_Pit_Insertion()
      throws InvalidDataExcecption, InvalidPositionException, OverflowException {
    landscapeObject.createPosition(0, 100);
    landscapeObject.createPosition(1, 3100);
    landscapeObject.createPosition(2, 1000);
    landscapeObject.createPosition(3, 900);

    // Adding water more than capacity
    landscapeObject.fillWater(0, 3200);
    landscapeObject.fillWater(1, 300);
    landscapeObject.fillWater(2, 3000);
  }

  @Test
  public void test_Valid_Hill_Insertion()
      throws InvalidDataExcecption, IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException, InvalidPositionException, OverflowException {
    landscapeObject.createPosition(0, 100);
    assertEquals(verifyCreatedPosition(landscapeObject, DATA_FILED_NAME, 0)._1.intValue(), 100);
    landscapeObject.createPosition(1, 3100);
    assertEquals(verifyCreatedPosition(landscapeObject, DATA_FILED_NAME, 1)._1.intValue(), 3100);
    landscapeObject.createPosition(2, 1000);
    assertEquals(verifyCreatedPosition(landscapeObject, DATA_FILED_NAME, 2)._1.intValue(), 1000);
    landscapeObject.createPosition(3, 900);
    assertEquals(verifyCreatedPosition(landscapeObject, DATA_FILED_NAME, 3)._1.intValue(), 900);
  }

  @Test
  public void test_Valid_Pit_Insertion()
      throws InvalidDataExcecption, IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException, InvalidPositionException, OverflowException {
    landscapeObject.createPosition(0, 100);
    landscapeObject.createPosition(1, 3100);
    landscapeObject.createPosition(2, 1000);
    landscapeObject.createPosition(3, 900);

    landscapeObject.fillWater(2, 1000);
    assertEquals(verifyCreatedPosition(landscapeObject, DATA_FILED_NAME, 2)._2.intValue(), 1000);
  }

  @Test
  public void test_CalculateDepositedWater()
      throws InvalidDataExcecption, InvalidPositionException, OverflowException {
    landscapeObject.createPosition(0, 100);
    landscapeObject.createPosition(1, 3100);
    landscapeObject.createPosition(2, 1000);
    landscapeObject.createPosition(3, 900);

    landscapeObject.fillWater(0, 1000);
    landscapeObject.fillWater(1, 100);
    landscapeObject.fillWater(2, 1000);
    landscapeObject.fillWater(3, 1000);

    assertEquals(3100, landscapeObject.calculateDepositedWater());
  }

  @Test(expected = OverflowException.class)
  public void testNegative_CalculateDepositedWater()
      throws InvalidDataExcecption, InvalidPositionException, OverflowException {
    landscapeObject.createPosition(0, 100);
    landscapeObject.createPosition(1, 3100);
    landscapeObject.createPosition(2, 1000);
    landscapeObject.createPosition(3, 900);

    landscapeObject.fillWater(0, 100);
    landscapeObject.fillWater(1, 1000);
    landscapeObject.fillWater(2, 1000);
    landscapeObject.fillWater(3, 1000);

    assertNotEquals(4000, landscapeObject.calculateDepositedWater());
  }

  @Test(expected = InvalidPositionException.class)
  public void testNegative_InsertWaterAtInvalidPositionBeforeCreatingAnPosition()
      throws InvalidDataExcecption, InvalidPositionException, OverflowException {

    landscapeObject.fillWater(0, 100);

    assertNotEquals(4000, landscapeObject.calculateDepositedWater());
  }

}

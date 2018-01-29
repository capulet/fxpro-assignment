package com.landscape.model.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.lang.ref.WeakReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.landscape.TestSuit;
import com.landscape.model.Landscape;
import com.landscape.model.exception.InvalidDataExcecption;
import com.landscape.model.exception.InvalidPositionException;
import com.landscape.model.exception.OverflowException;

public class LandscapeHelperTest extends TestSuit {

  Landscape landscapeObject = null;
  WeakReference<Landscape> refLandscapeObj = null;

  @Before
  public void setup() throws InvalidDataExcecption, InvalidPositionException, OverflowException {
    refLandscapeObj = new WeakReference<Landscape>(new Landscape());
    landscapeObject = refLandscapeObj.get();

    landscapeObject.createPosition(0, 100);
    landscapeObject.createPosition(1, 3100);
    landscapeObject.createPosition(2, 1000);
    landscapeObject.createPosition(3, 900);

    landscapeObject.fillWater(0, 100);
    landscapeObject.fillWater(2, 1000);
    landscapeObject.fillWater(3, 1000);
  }

  @After
  public void tearDown() {
    refLandscapeObj.clear();
  }

  @Test
  public void test_FillPit() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException, InvalidPositionException {
    assertTrue(LandscapeHelper.fillPit(DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME),
        0, 1000));
  }

  @Test
  public void testNegative_FillPit() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException, InvalidPositionException {
    assertEquals(false, LandscapeHelper
        .fillPit(DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME), 0, 3200));
  }

  @Test
  public void testGetCurrentLevel() throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException, InvalidPositionException {
    assertEquals(200, LandscapeHelper
        .getCurrentLevel(DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME), 0));
  }

  @Test
  public void testNegative_GetCurrentLevel() throws IllegalArgumentException,
      IllegalAccessException, NoSuchFieldException, SecurityException, InvalidPositionException {
    assertNotEquals(5000, LandscapeHelper
        .getCurrentLevel(DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME), 0));
  }

  @Test
  public void testGetMaximumWaterCanBeFilled() throws IllegalArgumentException,
      IllegalAccessException, NoSuchFieldException, SecurityException, InvalidPositionException {
    assertEquals(3000, LandscapeHelper
        .getMaximumWaterCanBeFilled(DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME), 0));
  }

  @Test
  public void testNegative_GetMaximumWaterCanBeFilled() throws IllegalArgumentException,
      IllegalAccessException, NoSuchFieldException, SecurityException, InvalidPositionException {
    assertNotEquals(5000, LandscapeHelper
        .getMaximumWaterCanBeFilled(DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME), 0));
  }

}

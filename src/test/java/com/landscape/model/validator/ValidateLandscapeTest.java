package com.landscape.model.validator;

import static com.landscape.model.LandscapConstants.MAX_HEIGHT;
import static com.landscape.model.LandscapConstants.MIN_HEIGHT;
import static org.junit.Assert.assertEquals;
import java.lang.ref.WeakReference;
import java.util.EnumMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.landscape.TestSuit;
import com.landscape.model.Landscape;
import com.landscape.model.exception.InvalidDataExcecption;
import com.landscape.model.exception.InvalidPositionException;
import com.landscape.model.exception.OverflowException;
import com.landscape.model.type.LandForms;

public class ValidateLandscapeTest extends TestSuit {

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

    landscapeObject.fillWater(0, 1000);
    landscapeObject.fillWater(0, 100);
    landscapeObject.fillWater(2, 1000);
    landscapeObject.fillWater(3, 1000);
  }

  @After
  public void tearDown() {
    refLandscapeObj.clear();
  }

  @Test(expected = InvalidDataExcecption.class)
  public void testInvalid_DataValidData()
      throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
      SecurityException, InvalidPositionException, OverflowException, InvalidDataExcecption {
    Map<Integer, EnumMap<LandForms, Integer>> value =
        DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME);
    ValidateLandscape.validateData(value, 0, 10000, false); // entering more than max height
  }

  @Test
  public void testInvalid_LandForm() {
    assertEquals(false, ValidateLandscape.validateLandscape(MAX_HEIGHT, MAX_HEIGHT));
    assertEquals(false, ValidateLandscape.validateLandscape(200, 31000)); // Height is > 3200
    assertEquals(false, ValidateLandscape.validateLandscape(-3200, -1));
    assertEquals(false, ValidateLandscape.validateLandscape(-3200, -3200));
  }

  @Test
  public void test_IsValidData()
      throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
      SecurityException, InvalidPositionException, OverflowException, InvalidDataExcecption {
    Map<Integer, EnumMap<LandForms, Integer>> value =
        DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME);
    assertEquals(true, ValidateLandscape.isValidData(value, 0, 1000, false));
  }

  @Test
  public void testNegative_IsValidData()
      throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
      SecurityException, InvalidPositionException, OverflowException, InvalidDataExcecption {
    Map<Integer, EnumMap<LandForms, Integer>> value =
        DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME);
    assertEquals(false, ValidateLandscape.isValidData(value, 0, -1000, false));
  }

  @Test
  public void testOverflowCase_ValidData()
      throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
      SecurityException, InvalidPositionException, OverflowException, InvalidDataExcecption {
    Map<Integer, EnumMap<LandForms, Integer>> value =
        DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME);
    ValidateLandscape.validateData(value, 0, 100, false); // entering more than capacity
  }

  @Test
  public void testValid_LandForm() {
    assertEquals(true, ValidateLandscape.validateLandscape(0, 0));
    assertEquals(true, ValidateLandscape.validateLandscape(20, 30));
    assertEquals(true, ValidateLandscape.validateLandscape(MIN_HEIGHT, MAX_HEIGHT));
    assertEquals(true, ValidateLandscape.validateLandscape(100, 3100));
  }
}

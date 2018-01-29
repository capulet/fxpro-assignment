package com.landscape;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.landscape.model.Landscape;
import com.landscape.model.Tuple;
import com.landscape.model.type.LandForms;

/**
 * Will contain the required setup for running the test in full swing
 *
 */
public class TestSuit {

  /**
   * Used only in case while testing requires special access to internal field for verifying the
   * behavior is as per design and cover more depth
   * 
   */
  protected static class DeEncapsulation {

    /**
     * 
     * Returns the non-accessible private field
     * 
     * @param object
     * @param fieldName
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getField(Object object, String fieldName) throws IllegalArgumentException,
        IllegalAccessException, NoSuchFieldException, SecurityException {
      Field field = object.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      T value = (T) field.get(object);
      return value;
    }

  }
  protected static final String DATA_FILED_NAME = "data";

  final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * Returns Hill and Pit for underlying given position
   * 
   * @param landscapeObject
   * @param dataFiledName
   * @param pos position
   * @return
   * @throws SecurityException
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   */
  protected Tuple<Integer, Integer> verifyCreatedPosition(Landscape landscapeObject,
      String dataFiledName, int pos) throws IllegalArgumentException, IllegalAccessException,
      NoSuchFieldException, SecurityException {
    Map<Integer, EnumMap<LandForms, Integer>> data =
        DeEncapsulation.getField(landscapeObject, DATA_FILED_NAME);
    Tuple<Integer, Integer> position = new Tuple<Integer, Integer>(
        data.get(pos).get(LandForms.HILLS), data.get(pos).get(LandForms.PITS));

    return position;
  }
}

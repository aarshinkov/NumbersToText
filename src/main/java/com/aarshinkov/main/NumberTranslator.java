package com.aarshinkov.main;

import java.util.*;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public abstract class NumberTranslator {

  protected Map<Integer, Integer> numberSections = new TreeMap<>();

  protected List<Long> digits = new ArrayList<>();
  protected Properties prop;

  protected String result = "";
  protected boolean hasPrevious = false;

  public abstract String getWholeNumberAsText(Long number);

  /**
   * Determines the level of a number. The levels are defined as number in range like this:<br/><br/>
   * 0-10:      level 1<br/>
   * 10-99:     level 2<br/>
   * 100-999:   level 3<br/>
   * 1000-9999  level 4<br/>
   * ...<br/>
   * so on.
   *
   * @param number the number to be  determined the level of
   *
   * @return the level of the number
   */
  public Integer getNumberLevel(Long number) {
    Integer result = 0;
    while (number != 0) {
      number = number / 10;
      result++;
    }
    return result;
  }

  protected void resetBases() {
    numberSections = new TreeMap<>();
    numberSections.put(1, -1);
    numberSections.put(2, -1);
    numberSections.put(3, -1);
    numberSections.put(4, -1);
    numberSections.put(5, -1);
    numberSections.put(6, -1);
    numberSections.put(7, -1);
    numberSections.put(8, -1);
    numberSections.put(9, -1);
    numberSections.put(10, -1);
    numberSections.put(11, -1);
    numberSections.put(12, -1);
    numberSections.put(13, -1);
  }

  public Integer getFirstDigitOfNumber(Long number) {
    return Integer.parseInt(Long.toString(number).substring(0, 1));
  }

  protected String getTextFromProp(String key) {
    return String.valueOf(prop.get(key));
  }
}

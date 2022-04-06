package com.aarshinkov.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public abstract class NumberTranslator {

  protected Properties prop;
  protected boolean hasPrevious = false;

  public NumberTranslator(String language) {
    try (InputStream input = NumberTranslator.class.getClassLoader().getResourceAsStream(language + ".properties")) {

      prop = new Properties();

      if (input == null) {
        System.out.println("Sorry, unable to find " + language + ".properties");
//        return "Error";
      }

      prop.load(input);

    } catch (IOException ex) {

    }
  }

  public abstract String getWholeNumberAsText(Long number);

  public List<Long> fragmentNumber(Long number) {

    final int startLevel = getNumberLevel(number);

    return fragmentNumber(number, startLevel);
  }

  private List<Long> fragmentNumber(Long number, Integer currentLevel) {

    final List<Long> digits = new ArrayList<>();
    long prevStep = 0L;

    do {
      final int power = (int) Math.pow(10, currentLevel - 1);

      final long base = ((number / power) * power);
      final long stepResult = base - prevStep;
      prevStep = base;

      digits.add(stepResult);

      if (currentLevel == 1) {
        return digits;
      }

      currentLevel--;

    } while (true);
  }

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
    if (number == 0) {
      return 1;
    }
    Integer result = 0;
    while (number != 0) {
      number = number / 10;
      result++;
    }
    return result;
  }

  protected Map<Integer, Integer> populateNumberSections(List<Long> numbers) {

    Map<Integer, Integer> numberSections = new TreeMap<>();

    if (numbers.isEmpty()) {
      return numberSections;
    }

//    resetBases();

    for (Long number : numbers) {
      numberSections.put(getNumberLevel(number), getFirstDigitOfNumber(number));
    }

    return numberSections;
  }

  public Integer getLargestKeyInMap(Map<Integer, Integer> map) {
    Integer result = 0;

    for (Integer number : map.keySet()) {
      if (number > result) {
        result = number;
      }
    }

    return result;
  }

//  protected void resetBases() {
//    numberSections = new TreeMap<>();
//    numberSections.put(1, -1);
//    numberSections.put(2, -1);
//    numberSections.put(3, -1);
//    numberSections.put(4, -1);
//    numberSections.put(5, -1);
//    numberSections.put(6, -1);
//    numberSections.put(7, -1);
//    numberSections.put(8, -1);
//    numberSections.put(9, -1);
//    numberSections.put(10, -1);
//    numberSections.put(11, -1);
//    numberSections.put(12, -1);
//    numberSections.put(13, -1);
//  }

  public Integer getFirstDigitOfNumber(Long number) {
    return Integer.parseInt(Long.toString(number).substring(0, 1));
  }

  protected String getTextFromProp(String key) {
    return String.valueOf(prop.get(key));
  }
}

package com.aarshinkov.main;

import com.aarshinkov.utils.*;
import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class PriceInWords {

  private final String language;

  private Map<Integer, Integer> numberSections = new TreeMap<>();

  private List<Long> digits = new ArrayList<>();
  private Properties prop;

  private String result = "";

  public PriceInWords() {
    this(Constants.DEFAULT_LANG);
  }

  public PriceInWords(String language) {
    if (language == null || language.trim().isEmpty()) {
      this.language = Constants.DEFAULT_LANG;
    } else {
      this.language = language.toLowerCase();
    }

    try (InputStream input = PriceInWords.class.getClassLoader().getResourceAsStream(language + ".properties")) {

      prop = new Properties();

      if (input == null) {
        System.out.println("Sorry, unable to find " + language + ".properties");
//        return "Error";
      }

      prop.load(input);

    } catch (IOException ex) {

    }
  }

  public String print(Long number) {

    result = "";
    final int startLevel = getNumberLevel(number);

    digits = new ArrayList<>();
    resetBases();
    if (number >= 0 && number <= 9) {
      digits.add(number);
      System.out.println(Arrays.toString(digits.toArray()));
      numberSections.put(1, number.intValue());
    } else {
      fragmentNumber(number, 0L, 0L, startLevel, startLevel);
      System.out.println(Arrays.toString(digits.toArray()));
      populateNumberSections(digits);
    }

    if (language.equals("bg")) {
      return getNumberAsBgText();
    } else {
      return "";
//      return getNumberAsEnText();
    }
  }

  private List<Long> fragmentNumber(Long number, Long stepsSum, Long prevStep, Integer originalLevel, Integer currentLevel) {

    final int power = (int) Math.pow(10, currentLevel - 1);

    long base = ((number / power) * power);
    long stepResult = base - prevStep;
    prevStep = base;

    digits.add(stepResult);
    if (currentLevel == 1) {
      return digits;
    }
    currentLevel--;
    return fragmentNumber(number, stepsSum, prevStep, originalLevel, currentLevel);
  }

  private void populateNumberSections(List<Long> numbers) {

    if (numbers.isEmpty()) {
      return;
    }

    resetBases();

    for (Long number : numbers) {
      numberSections.put(getNumberLevel(number), getFirstDigitOfNumber(number));
    }
  }

  boolean hasPrevious = false;

  private String getNumberAsBgText() {
    StringBuilder text = new StringBuilder();

    for (int section = numberSections.size(); section >= 1; section--) {
//      System.out.println("section-" + section + ": " + numberSections.get(section));
      final Integer num = numberSections.get(section);

      if (section != 1 && num != -1) {
        hasPrevious = true;
      }

      if (num == -1) {
        continue;
      }
//      System.out.println("Num: " + num);
//      System.out.println("Section: " + section);
//      System.out.println("---------------------");

      if (section == 1 && hasPrevious) {
        text.append(getTextFromProp("and")).append(" ");
      }

      String t = "";

      // Desetici
      if (section == 1) {
        if (num == 1) {
          t = getTextFromProp("num." + num);
        } else {
          t = getTextFromProp("num." + num);
        }
      } else if (section == 2) {
        if (num == 2) {
          t = getTextFromProp("num.20");
        } else {
          t = getTextFromProp("num." + num) + getTextFromProp("num.10");
        }
      } else if (section == 3) { // Stotici
        if (num == 2 || num == 3) {
          t = getTextFromProp("num." + num) + getTextFromProp("num.section." + section + ".1");
        } else {
          t = getTextFromProp("num." + num) + getTextFromProp("num.section." + section + ".n");
        }
      } else {
        if (num == 1) {
          t = getTextFromProp("num.section." + section + ".1");
        } else {
          t = getTextFromProp("num." + num) + " " + getTextFromProp("num.section." + section + ".n");
        }
      }

      text.append(t);
      if (section != 1) {
        text.append(" ");
      }
//      text.append(prop.get("num." + num)).append(t).append(" ");
    }

    return text.toString();
  }

  private void resetBases() {
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

  private String getTextFromProp(String key) {
    return String.valueOf(prop.get(key));
  }

  private String printFragment(List<Long> digits, Long number, Long stepsSum, Long prevStep, Integer originalLevel, Integer currentLevel) {

    final int power = (int) Math.pow(10, currentLevel - 1);

    long base = ((number / power) * power);
    long stepResult = base - prevStep;
    prevStep = base;

    if (currentLevel == 1) {
      result += stepResult;
      return result;
    }

    result += stepResult + ", ";

    currentLevel--;
    return printFragment(digits, number, stepsSum, prevStep, originalLevel, currentLevel);
  }
}

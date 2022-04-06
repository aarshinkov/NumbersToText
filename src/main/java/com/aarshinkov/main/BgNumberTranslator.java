package com.aarshinkov.main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class BgNumberTranslator extends NumberTranslator {

  public BgNumberTranslator() {
    super("bg");
  }

  @Override
  public String getWholeNumberAsText(Long number) {
    List<Long> digits = fragmentNumber(number);
    Map<Integer, Integer> sections = populateNumberSections(digits);

    StringBuilder text = new StringBuilder();

    boolean shouldSkipNextDigit = false;

    for (int section = getLargestKeyInMap(sections); section >= 1; section--) {

      if (!sections.containsKey(section)) {
        continue;
      }

      if (shouldSkipNextDigit) {
        continue;
      }

      final Integer num = sections.get(section);

      if (section != 1 && num != null) {
        hasPrevious = true;
      }
//      System.out.println("Num: " + num);
//      System.out.println("Section: " + section);
//      System.out.println("---------------------");

      if (section == 1 && hasPrevious) {
        if (num != 0) {
          text.append(getTextFromProp("and")).append(" ");
        }
      }

      String t = "";

      if (section == 1) {
        if (num != 0 || !hasPrevious) {
          t = getTextFromProp("num." + num);
        }
      } else if (section == 2) {

        final Integer nextDigit = sections.get(1);

        if (num == 1 && nextDigit != 0) {
          String base;
          if (nextDigit == 1) {
            base = getTextFromProp("num.11");
          } else if (nextDigit == 2) {
            base = getTextFromProp("num.12");
          } else {
            base = getTextFromProp("num." + nextDigit);
          }
          t = base + getTextFromProp("num.teens") + getTextFromProp("num.10");
          shouldSkipNextDigit = true;
        } else {
          if (num == 1) {
            t = getTextFromProp("num.10");
          } else if (num == 2) {
            t = getTextFromProp("num.20") + getTextFromProp("num.10");
          } else {
            t = getTextFromProp("num." + num) + getTextFromProp("num.10");
          }
        }
      } else if (section == 3) {
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
    }
//
    return text.toString().trim();
  }
}

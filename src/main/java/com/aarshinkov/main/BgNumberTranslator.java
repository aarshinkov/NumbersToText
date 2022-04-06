package com.aarshinkov.main;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class BgNumberTranslator extends NumberTranslator {

  @Override
  public String getWholeNumberAsText(Long number) {
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
}

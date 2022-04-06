package com.aarshinkov.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class BgNumberTranslatorTests {

  private NumberTranslator nt;

  @BeforeEach
  public void setUp() {
    nt = new BgNumberTranslator();
  }


  @Test
  void testNumber() {
    Assertions.assertEquals(500, nt.fragmentNumber(543L).get(0));
    Assertions.assertEquals(40, nt.fragmentNumber(543L).get(1));
    Assertions.assertEquals(3, nt.fragmentNumber(543L).get(2));
//    String result = "";
//
//    result = nt.fragmentNumber(50L);
//    System.out.println(result);
//    Assertions.assertEquals("петдесет", result);

//    System.out.println("===========================\nResult: " + piw.print(147L));
  }

  @Test
  void testFirstDigit() {
    Assertions.assertEquals(5, nt.getFirstDigitOfNumber(5431L));
    Assertions.assertEquals(1, nt.getFirstDigitOfNumber(1L));
    Assertions.assertEquals(8, nt.getFirstDigitOfNumber(85L));
    Assertions.assertEquals(9, nt.getFirstDigitOfNumber(926L));
  }

  @Test
  void testNumberLevel() {
    Assertions.assertEquals(1, nt.getNumberLevel(1L));
    Assertions.assertEquals(2, nt.getNumberLevel(12L));
    Assertions.assertEquals(3, nt.getNumberLevel(123L));
    Assertions.assertEquals(4, nt.getNumberLevel(1234L));
    Assertions.assertEquals(5, nt.getNumberLevel(12345L));
    Assertions.assertEquals(6, nt.getNumberLevel(123456L));
    Assertions.assertEquals(7, nt.getNumberLevel(1234567L));
    Assertions.assertEquals(8, nt.getNumberLevel(12345678L));
    Assertions.assertEquals(9, nt.getNumberLevel(123456789L));
    Assertions.assertEquals(10, nt.getNumberLevel(1234567890L));
    Assertions.assertEquals(11, nt.getNumberLevel(12345678901L));
    Assertions.assertEquals(12, nt.getNumberLevel(123456789012L));
  }
}

package com.aarshinkov.main;

import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class PriceInWordsTests {

  private PriceInWords piw;
//  private NumberTranslator nt;

  @BeforeEach
  public void setUp() {
    piw = new PriceInWords("bg");
  }


  @Test
  void testNumber() {
    String result = "";
//    System.out.println(result);
//    Assertions.assertEquals("нула", result);
//
//    result = piw.print(1L);
//    System.out.println(result);
//    Assertions.assertEquals("едно", result);
//
//    result = piw.print(2L);
//    System.out.println(result);
//    Assertions.assertEquals("две", result);
//
//    result = piw.print(3L);
//    System.out.println(result);
//    Assertions.assertEquals("три", result);
//
//    result = piw.print(4L);
//    System.out.println(result);
//    Assertions.assertEquals("четири", result);
//
//    result = piw.print(5L);
//    System.out.println(result);
//    Assertions.assertEquals("пет", result);
//
//    result = piw.print(6L);
//    System.out.println(result);
//    Assertions.assertEquals("шест", result);
//
//    result = piw.print(7L);
//    System.out.println(result);
//    Assertions.assertEquals("седем", result);
//
//    result = piw.print(8L);
//    System.out.println(result);
//    Assertions.assertEquals("осем", result);
//
//    result = piw.print(9L);
//    System.out.println(result);
//    Assertions.assertEquals("девет", result);

//    result = piw.print(10L);
//    System.out.println(result);
//    Assertions.assertEquals("десет", result);

    result = piw.print(50L);
    System.out.println(result);
    Assertions.assertEquals("петдесет", result);

    result = piw.print(53L);
    System.out.println(result);
    Assertions.assertEquals("петдесет и три", result);

    result = piw.print(532L);
    System.out.println(result);
    Assertions.assertEquals("петстотин тридесет и две", result);

    result = piw.print(2784L);
    System.out.println(result);
    Assertions.assertEquals("две хиляди седемстотин осемдесет и четири", result);

//    System.out.println("===========================\nResult: " + piw.print(147L));
  }

  @Test
  void testFirstDigit() {
    Assertions.assertEquals(5, piw.getFirstDigitOfNumber(5431L));
    Assertions.assertEquals(1, piw.getFirstDigitOfNumber(1L));
    Assertions.assertEquals(8, piw.getFirstDigitOfNumber(85L));
    Assertions.assertEquals(9, piw.getFirstDigitOfNumber(926L));
  }

  @Test
  void testNumberLevel() {
    Assertions.assertEquals(1, piw.getNumberLevel(1L));
    Assertions.assertEquals(2, piw.getNumberLevel(12L));
    Assertions.assertEquals(3, piw.getNumberLevel(123L));
    Assertions.assertEquals(4, piw.getNumberLevel(1234L));
    Assertions.assertEquals(5, piw.getNumberLevel(12345L));
    Assertions.assertEquals(6, piw.getNumberLevel(123456L));
    Assertions.assertEquals(7, piw.getNumberLevel(1234567L));
    Assertions.assertEquals(8, piw.getNumberLevel(12345678L));
    Assertions.assertEquals(9, piw.getNumberLevel(123456789L));
    Assertions.assertEquals(10, piw.getNumberLevel(1234567890L));
    Assertions.assertEquals(11, piw.getNumberLevel(12345678901L));
    Assertions.assertEquals(12, piw.getNumberLevel(123456789012L));
  }
}

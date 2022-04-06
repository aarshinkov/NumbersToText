package com.aarshinkov.main;

import org.junit.jupiter.api.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Tag("translators")
public class BgNumberTranslatorTests {

  private NumberTranslator nt;

  @BeforeEach
  public void setUp() {
    nt = new BgNumberTranslator();
  }

  @DisplayName("Test number fragmenting")
  @Test
  void testNumberFragment() {
    Assertions.assertEquals(1, nt.fragmentNumber(1L).get(0));

    Assertions.assertEquals(10, nt.fragmentNumber(10L).get(0));
    Assertions.assertEquals(0, nt.fragmentNumber(10L).get(1));

    Assertions.assertEquals(50000, nt.fragmentNumber(58974L).get(0));
    Assertions.assertEquals(8000, nt.fragmentNumber(58974L).get(1));
    Assertions.assertEquals(900, nt.fragmentNumber(58974L).get(2));
    Assertions.assertEquals(70, nt.fragmentNumber(58974L).get(3));
    Assertions.assertEquals(4, nt.fragmentNumber(58974L).get(4));
  }

  @DisplayName("Test getting whole number as text")
  @Test
  void testGettingWholeNumberAsText() {
    String result = "";

    result = nt.getWholeNumberAsText(0L);
    Assertions.assertEquals("нула", result);

    result = nt.getWholeNumberAsText(1L);
    Assertions.assertEquals("едно", result);

    result = nt.getWholeNumberAsText(2L);
    Assertions.assertEquals("две", result);

    result = nt.getWholeNumberAsText(3L);
    Assertions.assertEquals("три", result);

    result = nt.getWholeNumberAsText(4L);
    Assertions.assertEquals("четири", result);

    result = nt.getWholeNumberAsText(5L);
    Assertions.assertEquals("пет", result);

    result = nt.getWholeNumberAsText(6L);
    Assertions.assertEquals("шест", result);

    result = nt.getWholeNumberAsText(7L);
    Assertions.assertEquals("седем", result);

    result = nt.getWholeNumberAsText(8L);
    Assertions.assertEquals("осем", result);

    result = nt.getWholeNumberAsText(9L);
    Assertions.assertEquals("девет", result);

    result = nt.getWholeNumberAsText(10L);
    Assertions.assertEquals("десет", result);

    result = nt.getWholeNumberAsText(11L);
    Assertions.assertEquals("единадесет", result);

    result = nt.getWholeNumberAsText(12L);
    Assertions.assertEquals("дванадесет", result);

    result = nt.getWholeNumberAsText(16L);
    Assertions.assertEquals("шестнадесет", result);

    result = nt.getWholeNumberAsText(19L);
    Assertions.assertEquals("деветнадесет", result);

    result = nt.getWholeNumberAsText(20L);
    Assertions.assertEquals("двадесет", result);

    result = nt.getWholeNumberAsText(21L);
    Assertions.assertEquals("двадесет и едно", result);

    result = nt.getWholeNumberAsText(22L);
    Assertions.assertEquals("двадесет и две", result);

    result = nt.getWholeNumberAsText(50L);
    Assertions.assertEquals("петдесет", result);

    result = nt.getWholeNumberAsText(53L);
    Assertions.assertEquals("петдесет и три", result);

    result = nt.getWholeNumberAsText(98L);
    Assertions.assertEquals("деветдесет и осем", result);

    result = nt.getWholeNumberAsText(532L);
    Assertions.assertEquals("петстотин тридесет и две", result);

    result = nt.getWholeNumberAsText(2784L);
    Assertions.assertEquals("две хиляди седемстотин осемдесет и четири", result);

    result = nt.getWholeNumberAsText(5824L);
    Assertions.assertEquals("пет хиляди осемстотин двадесет и четири", result);
  }

  @DisplayName("Test getting first digit of a number")
  @Test
  void testFirstDigit() {
    Assertions.assertEquals(5, nt.getFirstDigitOfNumber(5431L));
    Assertions.assertEquals(1, nt.getFirstDigitOfNumber(1L));
    Assertions.assertEquals(8, nt.getFirstDigitOfNumber(85L));
    Assertions.assertEquals(9, nt.getFirstDigitOfNumber(926L));
  }

  @DisplayName("Test the level of number")
  @Test
  void testNumberLevel() {
    Assertions.assertEquals(1, nt.getNumberLevel(0L));
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

  @DisplayName("Test getting the largest key in a map")
  @Test
  void testGetLargestKeyInMap() {
    Map<Integer, Integer> map = new TreeMap<>();
    Assertions.assertEquals(0, nt.getLargestKeyInMap(map));

    map = new TreeMap<>();
    map.put(56, 66);
    Assertions.assertEquals(56, nt.getLargestKeyInMap(map));

    map = new TreeMap<>();
    map.put(1, 66);
    map.put(10, 66);
    map.put(2, 66);
    map.put(8, 66);
    map.put(5, 66);
    Assertions.assertEquals(10, nt.getLargestKeyInMap(map));
  }
}

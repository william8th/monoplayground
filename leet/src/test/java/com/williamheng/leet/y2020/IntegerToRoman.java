package com.williamheng.leet.y2020;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class IntegerToRoman {

    /**
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */

    /**
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
     * Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
     * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     * <p>
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     */

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {3, "III"},
                {4, "IV"},
                {9, "IX"},
                {12, "XII"},
                {27, "XXVII"},
                {58, "LVIII"},
                {1994, "MCMXCIV"},
                {3999, "MMMCMXCIX"},
        });
    }

    @Parameterized.Parameter
    public int input;

    @Parameterized.Parameter(1)
    public String output;

    @Test
    public void test() {
        assertThat(intToRoman(input), equalTo(output));
    }

    // TODO: This can be simplified!
    public String intToRoman(int num) {
        Map<Integer, String> lookup = new TreeMap<>(Collections.reverseOrder());
        lookup.put(1, "I");
        lookup.put(5, "V");
        lookup.put(10, "X");
        lookup.put(50, "L");
        lookup.put(100, "C");
        lookup.put(500, "D");
        lookup.put(1000, "M");

        Map<Integer, String> overrideLookup = new HashMap<>();
        overrideLookup.put(4, "IV");
        overrideLookup.put(9, "IX");
        overrideLookup.put(40, "XL");
        overrideLookup.put(90, "XC");
        overrideLookup.put(400, "CD");
        overrideLookup.put(900, "CM");

        String s = String.valueOf(num);
        StringBuilder result = new StringBuilder();
        Set<Map.Entry<Integer, String>> lookupSet = lookup.entrySet();

        for (int i = 0; i < s.length(); i++) {
            int multiplier = s.length() - 1 - i;
            int d = Character.getNumericValue(s.charAt(i));

            for (int j = 0; j < multiplier; j++) {
                d *= 10;
            }

            while (d != 0) {

                if (overrideLookup.containsKey(d)) {
                    result.append(overrideLookup.get(d));
                    d = 0;
                }

                Iterator<Map.Entry<Integer, String>> it = lookupSet.iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, String> entry = it.next();
                    if (d >= entry.getKey()) {
                        result.append(entry.getValue());
                        d -= entry.getKey();
                        break;
                    }
                }

            }

        }

        return result.toString();
    }
}

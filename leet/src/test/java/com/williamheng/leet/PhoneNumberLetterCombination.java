package com.williamheng.leet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class PhoneNumberLetterCombination {

    @Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {null, null},
                {"2", asList("a", "b", "c")},
                {"23", asList(
                        "ad",
                        "ae",
                        "af",
                        "bd",
                        "be",
                        "bf",
                        "cd",
                        "ce",
                        "cf"
                )}
        });
    }

    @Parameter
    public String digits;

    @Parameter(1)
    public List<String> expected;

    @Test
    public void test() {
        List<String> actual = letterCombinations(digits);
        System.out.println(actual);
        assertThat(actual, equalTo(expected));
    }

    static Map<Character, List<Character>> letterMap = new HashMap<>();
    static {
        letterMap.put('2', asList('a', 'b', 'c'));
        letterMap.put('3', asList('d', 'e', 'f'));
        letterMap.put('4', asList('g', 'h', 'i'));
        letterMap.put('5', asList('j', 'k', 'l'));
        letterMap.put('6', asList('m', 'n', 'o'));
        letterMap.put('7', asList('p', 'q', 'r', 's'));
        letterMap.put('8', asList('t', 'u', 'v'));
        letterMap.put('9', asList('w', 'x', 'y', 'z'));
    }


    List<String> letterCombinations(String digits) {
        if (digits == null) return null;

        List<String> result = new ArrayList<>();
        dfs(digits.toCharArray(), result, "", 0);

        return result;
    }

    void dfs(char[] digits, List<String> result, String word, int level) {
        if (word.length() == digits.length) {
            result.add(word);
            return;
        }

        List<Character> combinations = letterMap.get(digits[level]);
        for (Character c: combinations) {
            dfs(digits, result, word + c, level+1);
        }
    }

}

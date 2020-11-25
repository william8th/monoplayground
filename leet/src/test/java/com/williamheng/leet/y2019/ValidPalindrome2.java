package com.williamheng.leet.y2019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ValidPalindrome2 {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", true},
                {"aba", true},
                {"abca", true},
                {"aaa", true},
                {"abc", false},
                {"aabc", false},
                {"aaba", true}
        });
    }

    @Parameter
    public String input;

    @Parameter(1)
    public boolean expected;

    @Test
    public void test() {
        assertThat(
                String.format("'%s' is not a valid palindrome with potentially one character removed", input),
                validPalindrome(input),
                equalTo(expected)
        );
    }

    boolean validPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;

        boolean removalAllowed = s.length() % 2 == 0;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            char head = s.charAt(i);
            char tail = s.charAt(j);

            if (head != tail) {
                if (!removalAllowed) return false;

                removalAllowed = false;
            }
        }

        return true;
    }
}

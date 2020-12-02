package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidAnagram {

    @Test
    public void test() {
        assertThat(isAnagram("anagram", "nagaram"), equalTo(true));
        assertThat(isAnagram("rat", "car"), equalTo(false));
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);

        for (int i = 0; i < sChars.length; i++) {
            if (sChars[i] != tChars[i]) return false;
        }

        return true;
    }
}

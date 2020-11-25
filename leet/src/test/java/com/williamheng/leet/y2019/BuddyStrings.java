package com.williamheng.leet.y2019;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BuddyStrings {
    @Test
    public void testBuddy() {
        assertThat(buddyStrings("ab", "ba"), equalTo(true));
        assertThat(buddyStrings("ab", "ab"), equalTo(false));
        assertThat(buddyStrings("aa", "aa"), equalTo(true));
        assertThat(buddyStrings("aaaabc", "aaaacb"), equalTo(true));
        assertThat(buddyStrings("", "aaaacb"), equalTo(false));
        assertThat(buddyStrings("bac", "cab"), equalTo(true));
        assertThat(buddyStrings("abcaa", "abcbb"), equalTo(false));
        assertThat(buddyStrings("", ""), equalTo(false));
        assertThat(buddyStrings("abab", "abab"), equalTo(true));
    }

    public boolean buddyStrings(String A, String B) {

        char[] ac = A.toCharArray();
        char[] bc = B.toCharArray();

        if (ac.length != bc.length || ac.length == 0) return false;

        int firstSwapIndex = -1;
        char firstAcChar = '\0';
        char firstBcChar = '\0';

        int secondSwapIndex = -1;
        char secondAcChar = '\0';
        char secondBcChar = '\0';

        for (int i = 0; i < ac.length; i++) {
           if (ac[i] != bc[i]) {
               if (firstSwapIndex == -1) {
                   firstSwapIndex = i;
                   firstAcChar = ac[i];
                   firstBcChar = bc[i];
               } else if (secondSwapIndex == -1) {
                   secondSwapIndex = i;
                   secondAcChar = ac[i];
                   secondBcChar = bc[i];
               } else {
                   return false;
               }
           }
        }

        if (firstSwapIndex == -1 && secondSwapIndex == -1) {
            char search = ac[0];
            for (int i = 0; i < ac.length; i++) {
                if (ac[i] != search || bc[i] != search) return false;
            }
            return true;
        }

        return firstSwapIndex != -1 && secondSwapIndex != -1 &&
                firstAcChar == secondBcChar &&
                secondAcChar == firstBcChar;
    }
}

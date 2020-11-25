package com.williamheng.leet.y2019;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidParentheses {
    @Test
    public void testValidParentheses() {
        assertThat(isValid("()"), equalTo(true));
        assertThat(isValid("()[]{}"), equalTo(true));
        assertThat(isValid("(]"), equalTo(false));
        assertThat(isValid("([)]"), equalTo(false));
        assertThat(isValid("([])"), equalTo(true));
        assertThat(isValid(""), equalTo(true));
        assertThat(isValid("]"), equalTo(true));
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char closingBracket = closingBracket(stack.pop());
                if (closingBracket != c || closingBracket == '\0') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private char closingBracket(char openingBracket) {
        if (openingBracket == '(') return ')';
        if (openingBracket == '[') return ']';
        if (openingBracket == '{') return '}';

        return '\0';
    }
}

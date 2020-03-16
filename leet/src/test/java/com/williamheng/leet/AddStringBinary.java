package com.williamheng.leet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class AddStringBinary {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0", "0", "0"},
                {"0", "1", "1"},
                {"1", "1", "10"},
                {
                        "0100100",
                        "1001001",
                        "1101101"
                },
                {"11", "11", "110"}
        });
    }

    @Parameter(0)
    public String a;

    @Parameter(1)
    public String b;

    @Parameter(2)
    public String expected;

    @Test
    public void test() {
        assertThat(binarySum(a, b), equalTo(expected));
    }

    @Test
    public void testAlternative() {
        assertThat(betterBinarySum(a, b), equalTo(expected));
    }

    String betterBinarySum(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        String result = "";
        int carry = 0;

        while (i >= 0 || j >= 0 || carry == 1) {

            carry += (i >= 0) ? a.charAt(i) - '0' : 0;
            carry += (j >= 0) ? b.charAt(j) - '0' : 0;

            result = (char) (carry % 2 + '0') + result;

            carry /= 2;

            i--;
            j--;
        }

        return result;
    }

    String binarySum(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {

            int x = (i >= 0) ? numberAt(a, i) : 0;
            int y = (j >= 0) ? numberAt(b, j) : 0;

            int sum = x + y + carry;
            switch (sum) {
                case 3:
                    result.insert(0, "1");
                    carry = 1;
                    break;
                case 2:
                    result.insert(0, "0");
                    carry = 1;
                    break;
                case 1:
                    result.insert(0, "1");
                    carry = 0;
                    break;
                default:
                    result.insert(0, "0");
            }

            i--;
            j--;
        }

        return result.toString();
    }

    int numberAt(String x, int index) {
        if (x.charAt(index) == '0') {
            return 0;
        } else {
            return 1;
        }
    }
}

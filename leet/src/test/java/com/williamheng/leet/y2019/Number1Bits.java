package com.williamheng.leet.y2019;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Number1Bits {

    @Test
    public void testSolution() {
        assertThat(hammingWeight(00000000000000000000000000001011), equalTo(3));
        assertThat(hammingWeight(00000000000000000000000010000000), equalTo(1));
        assertThat(hammingWeight(-3), equalTo(31)); // 11111111111111111111111111111101
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >>> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

}

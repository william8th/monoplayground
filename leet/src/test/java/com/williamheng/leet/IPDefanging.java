package com.williamheng.leet;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class IPDefanging {

    @Test
    public void testDefanging() {
        assertThat(defangIPaddr("1.1.1.1"), equalTo("1[.]1[.]1[.]1"));
        assertThat(defangIPaddr("255.100.50.0"), equalTo("255[.]100[.]50[.]0"));
    }

    public String defangIPaddr(String address) {
        return String.join("[.]", address.split("\\."));
    }
}

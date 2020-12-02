package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class EncodeDecodeTinyURL {

    @Test
    public void test() {
        Codec c = new Codec();
        String url = "a URL";

        assertThat(c.decode(c.encode(url)), equalTo(url));
    }

    public class Codec {

        String template = "http://tinyurl.com/";
        Map<String, String> lookup = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            return null;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return null;
        }
    }

}

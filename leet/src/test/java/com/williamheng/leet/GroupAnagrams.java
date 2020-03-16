package com.williamheng.leet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class GroupAnagrams {

    @Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {null, null},
                {new String[]{}, Collections.emptyList()},
                {new String[]{"bat"}, singletonList(singletonList("bat"))},
                {
                        new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                        asList(
                                asList("ate", "eat", "tea"),
                                singletonList("bat"),
                                asList("nat", "tan")
                        )
                }
        });
    }

    @Parameter
    public String[] input;

    @Parameter(1)
    public List<List<String>> expected;

    @Test
    public void test() {
        assertThat(groupAnagrams(input), equalTo(expected));
    }

    List<List<String>> groupAnagrams(String[] input) {
        if (input == null) return null;

        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String s: input) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        List<List<String>> result = new ArrayList<>();
        for(List<String> list: map.values()) {
            Collections.sort(list);
            result.add(list);
        }

        return result;
    }

}

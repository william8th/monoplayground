package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GroupPeopleGivenSize {

    @Test
    public void test() {
        int[] groupSizes = new int[]{3, 3, 3, 3, 3, 1, 3};
        assertThat(groupThePeople(groupSizes), equalTo(
                asList(
                        singletonList(5),
                        asList(0, 1, 2),
                        asList(3, 4, 6)
                )
        ));
    }

    @Test
    public void test2() {
        int[] groupSizes = new int[]{2, 1, 3, 3, 3, 2};
        assertThat(groupThePeople(groupSizes), equalTo(
                asList(
                        singletonList(1),
                        asList(0, 5),
                        asList(2, 3, 4)
                )
        ));
    }

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        class Pair {
            Integer value;
            Integer index;

            public Pair(Integer value, Integer index) {
                this.value = value;
                this.index = index;
            }

            public Integer getValue() {
                return value;
            }

            public Integer getIndex() {
                return index;
            }
        }

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            pairs.add(new Pair(groupSizes[i], i));
        }

        pairs.sort(Comparator.comparing(Pair::getValue).thenComparing(Pair::getIndex));

        int i = 0;
        List<List<Integer>> result = new ArrayList<>();

        while (i < pairs.size()) {
            Pair p = pairs.get(i);

            List<Integer> indexList = new ArrayList<>();
            for (int j = 0; j < p.value; j++) {
                Pair pairToAdd = pairs.get(i + j);
                indexList.add(pairToAdd.index);
            }

            result.add(indexList);
            i += p.value; // Index has moved by this value
        }

        return result;
    }

}

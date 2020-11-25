package com.williamheng.leet.y2019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MeetingRoomsI {

    static class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                { null, true },
                { new Interval[]{}, true },
                { new Interval[]{ new Interval(0, 30) }, true },

                // [[0, 30],[5, 10],[15, 20]]
                {
                        new Interval[]{
                                new Interval(0, 30),
                                new Interval(5, 10),
                                new Interval(15, 20),
                        },
                        false
                },

                // [[0, 30],[31, 40],[45, 80]]
                {
                        new Interval[]{
                                new Interval(0, 30),
                                new Interval(31, 40),
                                new Interval(45, 80),
                        },
                        true
                },
        });
    }

    @Parameter
    public Interval[] intervals;

    @Parameter(1)
    public boolean expected;

    @Test
    public void test() {
        assertThat(canAttendMeetings(intervals), equalTo(expected));
    }

    boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 0) return true;

        PriorityQueue<Interval> queue =  new PriorityQueue<>(Comparator.comparingInt(i -> i.start));
        queue.addAll(Arrays.asList(intervals));

        while(!queue.isEmpty()) {
            Interval now = queue.poll();
            Interval next = queue.peek();

            if (next != null && now.end > next.start) return false;
        }

        return true;
    }
}

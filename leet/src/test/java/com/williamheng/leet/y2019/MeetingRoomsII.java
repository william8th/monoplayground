package com.williamheng.leet.y2019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MeetingRoomsII {

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
                { null, 0 },
                { new Interval[]{}, 0 },
                { new Interval[]{ new Interval(0, 30) }, 1 },

                // [[0, 30],[5, 10],[15, 20]]
                {
                        new Interval[]{
                                new Interval(0, 30),
                                new Interval(5, 10),
                                new Interval(15, 20),
                        },
                        2
                },

                // [[0, 30],[31, 40],[45, 80]]
                {
                        new Interval[]{
                                new Interval(0, 30),
                                new Interval(31, 40),
                                new Interval(45, 80),
                        },
                        1
                },

                {
                        new Interval[]{
                                new Interval(0, 30),
                                new Interval(31, 40),
                                new Interval(31, 80),
                        },
                        2
                },

                {
                        new Interval[]{
                                new Interval(0, 30),
                                new Interval(0, 40),
                                new Interval(0, 80),
                        },
                        3
                },
        });
    }

    @Parameter
    public Interval[] intervals;

    @Parameter(1)
    public int expected;

    @Test
    public void test() {
        assertThat(minMeetingRooms(intervals), equalTo(expected));
    }

    @Test
    public void testSweepLine() {
        assertThat(sweepLineMeetingRooms(intervals), equalTo(expected));
    }

    int minMeetingRooms(Interval[] intervals) {
        if (intervals == null) return 0;
        if (intervals.length <= 1) return intervals.length;

        Queue<Interval> queue = new PriorityQueue<>(Comparator.comparingInt(i -> i.start));
        queue.addAll(Arrays.asList(intervals));

        List<Interval> roomsNeeded = new ArrayList<>();
        while (!queue.isEmpty()) {
            Interval current = queue.poll();
            Interval next = queue.peek();

            if (next == null || (next != null && current.end > next.start)) {

                boolean roomReusable = false;
                for (Interval i: roomsNeeded) {
                    if (i.end < current.start) {
                        // Room re-use
                        roomReusable = true;
                        i.end = current.end;
                        break;
                    }
                }

                if (!roomReusable) {
                    roomsNeeded.add(current);
                }
            }
        }

        return roomsNeeded.size();
    }

    class Point {
        int position;
        int flag;

        Point(int position, int flag) { this.position = position; this.flag = flag; }
    }

    int sweepLineMeetingRooms(Interval[] intervals) {

        if (intervals == null) return 0;
        if (intervals.length <= 1) return intervals.length;

        Queue<Point> points = new PriorityQueue<>(Comparator.comparingInt(p -> p.position));
        for (Interval i: intervals) {
            points.add(new Point(i.start, 1));
            points.add(new Point(i.end, -1));
        }

        int max = 0;
        int count = 0;
        while (!points.isEmpty()) {
            Point p = points.poll();
            count += p.flag;

            max = Math.max(count, max);
        }

        return max;
    }
}

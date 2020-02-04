package com.williamheng.leet;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CourseSchedule2 {

    @Test
    public void testSolution() {
        assertThat(findOrder(4, new int[][]{
                new int[]{1, 0},
                new int[]{2, 0},
                new int[]{3, 1},
                new int[]{3, 2}
        }), equalTo(new int[]{0, 1, 2, 3}));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> index = new HashMap<>();

        for (int[] pair : prerequisites) {
            int course = pair[0];
            int dependentCourse = pair[1];

            if (index.containsKey(course)) {
                index.get(course).add(dependentCourse);
            } else {
                index.put(course, new ArrayList<>(dependentCourse));
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int target = numCourses - 1;

        queue.add(0);
        int[] result = new int[numCourses];

        int i = 0;
        while (!queue.isEmpty()) {
            result[i] = queue.remove();
            List<Integer> dependentCourses = index.getOrDefault(0, new ArrayList<>());
        }

        return new int[]{};
    }

}

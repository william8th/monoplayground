package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LadderLength {

    @Test
    public void test() {
        assertThat(ladderLength("hit", "cog", Arrays.asList(
                "hot",
                "dot",
                "dog",
                "lot",
                "log",
                "cog"
        )), equalTo(5));

        assertThat(ladderLength("hit", "cog", Arrays.asList(
                "hot",
                "dot",
                "dog",
                "lot",
                "log"
        )), equalTo(0));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // Pre-processing
        // Intermediate state d*g -> [dig, dog]
        Map<String, List<String>> dictionary = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                char[] dictionaryKey = new char[word.length()];
                for (int j = 0; j < word.length(); j++) {
                    if (i == j) dictionaryKey[i] = '*';
                    else dictionaryKey[j] = word.charAt(j);
                }
                String intermediateKey = String.valueOf(dictionaryKey);
                List<String> transformations = dictionary.getOrDefault(intermediateKey, new ArrayList<>());
                transformations.add(word);
                dictionary.put(intermediateKey, transformations);
            }
        }

        Map<String, Integer> cost = new HashMap<>();
        Queue<String> toVisit = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        toVisit.add(beginWord);
        cost.put(beginWord, 1);

        while (!toVisit.isEmpty()) {

            String node = toVisit.remove();
            List<String> neighbours = new ArrayList<>();

            for (int i = 0; i < node.length(); i++) {
                char[] intermediateKey = new char[node.length()];
                for (int j = 0; j < node.length(); j++) {
                    if (i == j) intermediateKey[i] = '*';
                    else intermediateKey[j] = node.charAt(j);
                }

                neighbours.addAll(dictionary.getOrDefault(String.valueOf(intermediateKey), Collections.emptyList()));
            }

            for (String neighbour: neighbours) {
                int potentialCost = cost.get(node) + 1;
                if (cost.containsKey(neighbour)) {
                    int neighbourCost = cost.get(neighbour);
                    cost.put(neighbour, Math.min(potentialCost, neighbourCost));
                } else {
                    cost.put(neighbour, potentialCost);
                }

                if (!visited.contains(neighbour)) {
                    toVisit.add(neighbour);
                }
            }

            visited.add(node);
        }

        return cost.getOrDefault(endWord, 0);
    }

}

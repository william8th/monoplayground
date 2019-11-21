package com.williamheng.leet;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LRU {

    @Test
    public void test() {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1), equalTo(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        assertThat(cache.get(2), equalTo(-1));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        assertThat(cache.get(1), equalTo(-1));       // returns -1 (not found)
        assertThat(cache.get(3), equalTo(3));       // returns 3
        assertThat(cache.get(4), equalTo(4));       // returns 4
    }

    @Test
    public void test2() {
        LRUCache cache = new LRUCache(1 /* capacity */);

        cache.put(2, 1);
        assertThat(cache.get(2), equalTo(1));       // returns 1
        cache.put(3, 2);    // evicts key 2
        assertThat(cache.get(2), equalTo(-1));       // returns -1 (not found)
        assertThat(cache.get(3), equalTo(2));       // returns 2
    }

    class LRUCache {

        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        int capacity;
        Map<Integer, Node> index;
        Node root;
        Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            index = new HashMap<>(capacity);
        }

        public int get(int key) {
            if (index.containsKey(key)) {
                Node node = index.get(key);
                bubbleUp(node);
                return node.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (index.containsKey(key)) {
                bubbleUp(index.get(key));
            } else {

                if (index.size() == capacity) {
                    // evict
                    index.remove(tail.key);
                    Node prevNode = tail.prev;
                    if (prevNode != null) {
                        prevNode.next = null;
                        tail = prevNode;
                    }
                }

                Node node = new Node(key, value);

                if (root != null) {
                    node.next = root;
                    node.prev = root.prev;
                    root.prev = node;
                } else {
                    tail = node;
                }

                root = node;
                index.put(key, node);
            }
        }

        private void bubbleUp(Node node) {
            Node nextNode = node.next;
            Node prevNode = node.prev;

            if (prevNode != null) {
                node.prev = prevNode.prev;
                node.next = prevNode;
                prevNode.prev = node;
                prevNode.next = nextNode;
            }

            if (node.prev == null) {
                root = node;
            }

            if (nextNode == null && prevNode != null) {
                tail = prevNode;
            }
        }
    }
}

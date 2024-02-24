package com.lechros.psutil;

import java.math.BigInteger;

public class IntTrie {
    public final Node root;
    private final int length;

    public IntTrie(int length) {
        root = new Node();
        this.length = length;
    }

    public int length() {
        return length;
    }

    public void add(int value) {
        if (value >= (1 << length)) {
            throw new RuntimeException();
        }
        Node node = root;
        for (int i = length - 1; i >= 0; i--) {
            node = node.putIfAbsent(value & (1 << i));
        }
    }

    public static class Node {
        private Node zero, one;

        public Node get(int bit) {
            return bit == 0 ? zero : one;
        }

        public boolean has(int bit) {
            return get(bit) != null;
        }

        Node putIfAbsent(int bit) {
            if (bit == 0) {
                if (zero == null) {
                    zero = new Node();
                }
                return zero;
            } else {
                if (one == null) {
                    one = new Node();
                }
                return one;
            }
        }
    }
}

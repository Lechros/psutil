package com.lechros.psutil;

public class CharTrie {
    public final Node root;

    public CharTrie(char min, char max) {
        root = new Node(min, max - min + 1);
    }

    public void add(String str) {
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            node = node.putIfAbsent(str.charAt(i));
        }
        node.nul = true;
    }

    public static class Node {
        private Node[] children;
        private boolean nul;
        private int cnt;
        private char offset;

        public Node(char offset, int size) {
            this.offset = offset;
            this.children = new Node[size];
        }

        public boolean hasNul() {
            return nul;
        }

        public int count() {
            return cnt;
        }

        public Node get(char ch) {
            int i = ch - offset;
            return children[i];
        }

        Node putIfAbsent(char ch) {
            int i = ch - offset;
            if (children[i] == null) {
                children[i] = new Node(offset, children.length);
                cnt++;
            }
            return children[i];
        }
    }
}
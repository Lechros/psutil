package com.lechros.psutil;

import java.util.ArrayDeque;
import java.util.Queue;

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

    public void build() {
        Queue<Node> q = new ArrayDeque<>();
        root.fail = root;
        q.offer(root);
        int size = root.children.length;
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < size; i++) {
                Node child = node.children[i];
                if (child == null) continue;
                if (node == root) {
                    child.fail = root;
                } else {
                    Node fail = node.fail;
                    while (fail != root && fail.children[i] == null) {
                        fail = fail.fail;
                    }
                    if (fail.children[i] != null) {
                        fail = fail.children[i];
                    }
                    child.fail = fail;
                }
                if (child.fail.hasNul()) {
                    child.nul = true;
                }
                q.offer(child);
            }
        }
    }

    public boolean containsSubstring(String str) {
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            while (node != root && node.get(ch) == null) {
                node = node.fail;
            }
            if (node.get(ch) != null) {
                node = node.get(ch);
            }
            if (node.hasNul()) {
                return true;
            }
        }
        return false;
    }

    public static class Node {
        private Node[] children;
        private char offset;
        private boolean nul;
        private int cnt;
        private Node fail;

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
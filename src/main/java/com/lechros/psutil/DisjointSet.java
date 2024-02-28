package com.lechros.psutil;

public class DisjointSet {
    private final int[] parent;
    private int subsets;

    public DisjointSet(int size) {
        parent = new int[size];
        for (int i = 1; i < size; i++) {
            parent[i] = i;
        }
        subsets = size;
    }

    public int size() {
        return parent.length;
    }

    public int subsetCount() {
        return subsets;
    }

    public int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        parent[b] = a;
        subsets--;
        return true;
    }

    public boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }
}

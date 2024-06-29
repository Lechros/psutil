package com.lechros.psutil.segmenttree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortTree {
    private final int[][] tree;
    private final int n;

    public MergeSortTree(int[] arr) {
        this(arr.length);
        for (int i = 0; i < arr.length; i++) {
            set(i, arr[i]);
        }
        build();
    }

    public MergeSortTree(int n) {
        this.n = n;
        int h = 32 - Integer.numberOfLeadingZeros(n - 1);
        int s = 2 << h;
        tree = new int[s][];
        final int[] EMPTY = new int[0];
        Arrays.fill(tree, EMPTY);
    }

    public void build() {
        for (int i = n - 1; i > 0; i--) {
            tree[i] = merge(tree[i << 1], tree[(i << 1) + 1]);
        }
    }

    public int get(int index) {
        return tree[index + n][0];
    }

    public void set(int index, int value) {
        tree[index + n] = new int[]{value};
    }

    public int size() {
        return n;
    }

    public List<int[]> query(int start, int end) {
        List<int[]> res = new ArrayList<>();
        int s = start + n;
        int e = end + n;
        while (s <= e) {
            if ((s & 1) == 1) {
                res.add(tree[s++]);
            }
            if ((e & 1) == 0) {
                res.add(tree[e--]);
            }
            s >>= 1;
            e >>= 1;
        }
        return res;
    }

    public void update(int index, int value) {
        int i = index + n;
        tree[i][0] = value;
        while (i > 1) {
            i >>= 1;
            tree[i] = merge(tree[i << 1], tree[(i << 1) + 1]);
        }
    }

    private int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            merged[k++] = a[i] < b[j] ? a[i++] : b[j++];
        }
        while (i < a.length) merged[k++] = a[i++];
        while (j < b.length) merged[k++] = b[j++];
        return merged;
    }
}

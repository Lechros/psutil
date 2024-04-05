package com.lechros.psutil.fenwicktree;

public class IntFenwickTree {
    private final int n;
    private final int[] arr;
    private final int[] tree;

    public IntFenwickTree(int[] arr) {
        n = arr.length;
        this.arr = arr;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            updateDiff(i + 1, arr[i]);
        }
    }

    public IntFenwickTree(int n) {
        this.n = n;
        arr = new int[n];
        tree = new int[n + 1];
    }

    public int size() {
        return n;
    }

    public int sum(int start, int end) {
        return sum(end + 1) - sum(start);
    }

    private int sum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index &= index - 1;
        }
        return sum;
    }

    public void update(int index, int value) {
        int diff = value - arr[index];
        arr[index] = value;

        updateDiff(index + 1, diff);
    }

    private void updateDiff(int index1, int diff) {
        while (index1 <= n) {
            tree[index1] += diff;
            index1 += (index1 & -index1);
        }
    }
}

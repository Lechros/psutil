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
            updateDiff(i, arr[i]);
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
        updateDiff(index, diff);
    }

    public void updateDiff(int index, int diff) {
        arr[index] += diff;
        index++;
        while (index <= n) {
            tree[index] += diff;
            index += (index & -index);
        }
    }
}

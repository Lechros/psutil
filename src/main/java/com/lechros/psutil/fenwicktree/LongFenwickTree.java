package com.lechros.psutil.fenwicktree;

public class LongFenwickTree {
    private final int n;
    private final long[] arr;
    private final long[] tree;

    public LongFenwickTree(long[] arr) {
        n = arr.length;
        this.arr = arr;
        tree = new long[n + 1];
        for (int i = 0; i < n; i++) {
            updateDiff(i + 1, arr[i]);
        }
    }

    public LongFenwickTree(int n) {
        this.n = n;
        arr = new long[n];
        tree = new long[n + 1];
    }

    public int size() {
        return n;
    }

    public long sum(int start, int end) {
        return sum(end + 1) - sum(start);
    }

    private long sum(int index) {
        long sum = 0;
        while (index > 0) {
            sum += tree[index];
            index &= index - 1;
        }
        return sum;
    }

    public void update(int index, long value) {
        long diff = value - arr[index];
        arr[index] = value;

        updateDiff(index + 1, diff);
    }

    private void updateDiff(int index1, long diff) {
        while (index1 <= n) {
            tree[index1] += diff;
            index1 += (index1 & -index1);
        }
    }
}

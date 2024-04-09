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
            updateDiff(i, arr[i]);
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
        updateDiff(index, diff);
    }

    public void updateDiff(int index, long diff) {
        arr[index] += diff;
        index++;
        while (index <= n) {
            tree[index] += diff;
            index += (index & -index);
        }
    }
}

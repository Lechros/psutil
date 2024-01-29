package com.lechros.psutil;

import java.util.function.LongBinaryOperator;

public class LongSegmentTree {
    private long[] tree;
    private int n;
    private LongBinaryOperator op;
    private long initial;

    public LongSegmentTree(long[] arr, LongBinaryOperator op, long initial) {
        this(arr.length, op, initial);
        System.arraycopy(arr, 0, tree, n - 1, n);
        build();
    }

    public LongSegmentTree(int n, LongBinaryOperator op, long initial) {
        this.n = n;
        int h = 32 - Integer.numberOfLeadingZeros(n - 1);
        int s = 2 * (1 << h) - 1;
        tree = new long[s];
        this.op = op;
        this.initial = initial;
    }

    public void build() {
        for (int i = n - 2; i >= 0; i--) {
            tree[i] = op.applyAsLong(tree[(i << 1) + 1], tree[(i << 1) + 2]);
        }
    }

    public void set(int index, long value) {
        tree[index + n - 1] = value;
    }

    public int size() {
        return n;
    }

    public long query(int start, int end) {
        long res = initial;
        int s = start + n - 1;
        int e = end + n - 1;
        while (s <= e) {
            if (s % 2 == 0) {
                res = op.applyAsLong(res, tree[s++]);
            }
            if (e % 2 == 1) {
                res = op.applyAsLong(res, tree[e--]);
            }
            s = (s - 1) >> 1;
            e = (e - 1) >> 1;
        }
        return res;
    }

    public void update(int index, long value) {
        int i = index + n - 1;
        tree[i] = value;
        while (i > 0) {
            i = (i - 1) >> 1;
            tree[i] = op.applyAsLong(tree[(i << 1) + 1], tree[(i << 1) + 2]);
        }
    }
}

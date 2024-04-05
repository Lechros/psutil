package com.lechros.psutil;

import java.util.function.LongBinaryOperator;

public class LongSegmentTree {
    private final long[] tree;
    private final int n;
    private final LongBinaryOperator op;
    private final long initial;

    public LongSegmentTree(long[] arr, LongBinaryOperator op, long initial) {
        this(arr.length, op, initial);
        System.arraycopy(arr, 0, tree, n, n);
        build();
    }

    public LongSegmentTree(int n, LongBinaryOperator op, long initial) {
        this.n = n;
        int h = 32 - Integer.numberOfLeadingZeros(n - 1);
        int s = 2 << h;
        tree = new long[s];
        this.op = op;
        this.initial = initial;
    }

    public void build() {
        for (int i = n - 1; i > 0; i--) {
            tree[i] = op.applyAsLong(tree[i << 1], tree[(i << 1) + 1]);
        }
    }

    public void set(int index, long value) {
        tree[index + n] = value;
    }

    public int size() {
        return n;
    }

    public long query(int start, int end) {
        long res = initial;
        int s = start + n;
        int e = end + n;
        while (s <= e) {
            if ((s & 1) == 1) {
                res = op.applyAsLong(res, tree[s++]);
            }
            if ((e & 1) == 0) {
                res = op.applyAsLong(res, tree[e--]);
            }
            s >>= 1;
            e >>= 1;
        }
        return res;
    }

    public void update(int index, long value) {
        int i = index + n;
        tree[i] = value;
        while (i > 0) {
            tree[i >> 1] = op.applyAsLong(tree[i], tree[i + 1]);
            i >>= 1;
        }
    }
}

package com.lechros.psutil.segmenttree;

import java.util.function.IntBinaryOperator;

public class IntSegmentTree {
    private final int[] tree;
    private final int n;
    private final IntBinaryOperator op;
    private final int initial;

    public IntSegmentTree(int[] arr, IntBinaryOperator op, int initial) {
        this(arr.length, op, initial);
        System.arraycopy(arr, 0, tree, n, n);
        build();
    }

    public IntSegmentTree(int n, IntBinaryOperator op, int initial) {
        this.n = n;
        int h = 32 - Integer.numberOfLeadingZeros(n - 1);
        int s = 2 << h;
        tree = new int[s];
        this.op = op;
        this.initial = initial;
    }

    public void build() {
        for (int i = n - 1; i > 0; i--) {
            tree[i] = op.applyAsInt(tree[i << 1], tree[(i << 1) + 1]);
        }
    }

    public void set(int index, int value) {
        tree[index + n] = value;
    }

    public int size() {
        return n;
    }

    public int query(int start, int end) {
        int res = initial;
        int s = start + n;
        int e = end + n;
        while (s <= e) {
            if ((s & 1) == 1) {
                res = op.applyAsInt(res, tree[s++]);
            }
            if ((e & 1) == 0) {
                res = op.applyAsInt(res, tree[e--]);
            }
            s >>= 1;
            e >>= 1;
        }
        return res;
    }

    public void update(int index, int value) {
        int i = index + n;
        tree[i] = value;
        while (i > 1) {
            i >>= 1;
            tree[i] = op.applyAsInt(tree[i << 1], tree[(i << 1) + 1]);
        }
    }
}

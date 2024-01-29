package com.lechros.psutil;

import java.util.function.IntBinaryOperator;

public class IntSegmentTree {
    private int[] tree;
    private int n;
    private IntBinaryOperator op;
    private int initial;

    public IntSegmentTree(int[] arr, IntBinaryOperator op, int initial) {
        this(arr.length, op, initial);
        System.arraycopy(arr, 0, tree, n - 1, n);
        build();
    }

    public IntSegmentTree(int n, IntBinaryOperator op, int initial) {
        this.n = n;
        int h = 32 - Integer.numberOfLeadingZeros(n - 1);
        int s = 2 * (1 << h) - 1;
        tree = new int[s];
        this.op = op;
        this.initial = initial;
    }

    public void build() {
        for (int i = n - 2; i >= 0; i--) {
            tree[i] = op.applyAsInt(tree[(i << 1) + 1], tree[(i << 1) + 2]);
        }
    }

    public void set(int index, int value) {
        tree[index + n - 1] = value;
    }

    public int size() {
        return n;
    }

    public int query(int start, int end) {
        int res = initial;
        int s = start + n - 1;
        int e = end + n - 1;
        while (s <= e) {
            if (s % 2 == 0) {
                res = op.applyAsInt(res, tree[s++]);
            }
            if (e % 2 == 1) {
                res = op.applyAsInt(res, tree[e--]);
            }
            s = (s - 1) >> 1;
            e = (e - 1) >> 1;
        }
        return res;
    }

    public void update(int index, int value) {
        int i = index + n - 1;
        tree[i] = value;
        while (i > 0) {
            i = (i - 1) >> 1;
            tree[i] = op.applyAsInt(tree[(i << 1) + 1], tree[(i << 1) + 2]);
        }
    }
}

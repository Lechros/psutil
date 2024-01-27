package com.lechros.psutil;

import java.util.function.BinaryOperator;

public class SegmentTree<T> {
    private T[] tree;
    private int n;
    private BinaryOperator<T> op;
    private T initial;

    public SegmentTree(T[] arr, BinaryOperator<T> op, T initial) {
        n = arr.length;
        int h = 33 - Integer.numberOfLeadingZeros(n - 1);
        int s = 2 * (1 << h) - 1;
        tree = (T[]) new Object[s];
        this.op = op;
        this.initial = initial;
        build(arr);
    }

    private void build(T[] arr) {
        System.arraycopy(arr, 0, tree, n - 1, n);
        for (int i = n - 2; i >= 0; i--) {
            tree[i] = op.apply(tree[(i << 1) + 1], tree[(i << 1) + 2]);
        }
    }

    public int size() {
        return n;
    }

    public T query(int start, int end) {
        T res = initial;
        int s = start + n - 1;
        int e = end + n - 1;
        while (s <= e) {
            if (s % 2 == 0) {
                res = op.apply(res, tree[s++]);
            }
            if (e % 2 == 1) {
                res = op.apply(res, tree[e--]);
            }
            s = (s - 1) >> 1;
            e = (e - 1) >> 1;
        }
        return res;
    }

    public void update(int index, T value) {
        int i = index + n - 1;
        tree[i] = value;
        while (i > 0) {
            i = (i - 1) >> 1;
            tree[i] = op.apply(tree[(i << 1) + 1], tree[(i << 1) + 2]);
        }
    }
}

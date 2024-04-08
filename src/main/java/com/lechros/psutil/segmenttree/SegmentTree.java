package com.lechros.psutil.segmenttree;

import java.util.function.BinaryOperator;

public class SegmentTree<T> {
    private final T[] tree;
    private final int n;
    private final BinaryOperator<T> op;
    private final T initial;

    public SegmentTree(T[] arr, BinaryOperator<T> op, T initial) {
        this(arr.length, op, initial);
        System.arraycopy(arr, 0, tree, n, n);
        build();
    }

    public SegmentTree(int n, BinaryOperator<T> op, T initial) {
        this.n = n;
        int h = 32 - Integer.numberOfLeadingZeros(n - 1);
        int s = 2 << h;
        tree = (T[]) new Object[s];
        this.op = op;
        this.initial = initial;
    }

    public void build() {
        for (int i = n - 1; i > 0; i--) {
            tree[i] = op.apply(tree[i << 1], tree[(i << 1) + 1]);
        }
    }

    public T get(int index) {
        return tree[index + n];
    }

    public void set(int index, T value) {
        tree[index + n] = value;
    }

    public int size() {
        return n;
    }

    public T query(int start, int end) {
        T res = initial;
        int s = start + n;
        int e = end + n;
        while (s <= e) {
            if ((s & 1) == 1) {
                res = op.apply(res, tree[s++]);
            }
            if ((e & 1) == 0) {
                res = op.apply(res, tree[e--]);
            }
            s >>= 1;
            e >>= 1;
        }
        return res;
    }

    public void update(int index, T value) {
        int i = index + n;
        tree[i] = value;
        while (i > 1) {
            i >>= 1;
            tree[i] = op.apply(tree[i << 1], tree[(i << 1) + 1]);
        }
    }
}

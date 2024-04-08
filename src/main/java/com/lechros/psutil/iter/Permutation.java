package com.lechros.psutil.iter;

public class Permutation {
    public static boolean nextPermutation(int[] p) {
        for (int i = p.length - 2; i >= 0; i--) {
            if (p[i] < p[i + 1]) {
                for (int j = p.length - 1; ; j--) {
                    if (p[j] > p[i]) {
                        int t = p[i];
                        p[i] = p[j];
                        p[j] = t;
                        for (i++, j = p.length - 1; i < j; i++, j--) {
                            t = p[i];
                            p[i] = p[j];
                            p[j] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void permutation(int[] arr, int r) {
        permutation(arr, r, new int[r], 0, new boolean[arr.length]);
    }

    static void permutation(int[] arr, int r, int[] selected, int d, boolean[] visited) {
        if (d == r) {
            /* Consumer(selected) */
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[d] = arr[i];
            permutation(arr, r, selected, d + 1, visited);
            visited[i] = false;
        }
    }
}

package com.lechros.psutil.math;

public class Pow {
    public static int pow(int a, int n, int m) {
        long res = 1L;
        a %= m;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = (res * a) % m;
            }
            a = (a * a) % m;
            n >>= 1;
        }
        return (int) res;
    }

    public static long pow(long a, long n, long m) {
        long res = 1L;
        a %= m;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = (res * a) % m;
            }
            a = (a * a) % m;
            n >>= 1;
        }
        return res;
    }
}

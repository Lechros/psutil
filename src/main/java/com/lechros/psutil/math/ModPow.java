package com.lechros.psutil.math;

public class ModPow {
    public static int modPow(int a, int n, int m) {
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

    public static long modPow(long a, long n, long m) {
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

package com.lechros.psutil;

public class Maths {
    public static int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        while (b > 0) {
            int q = a / b;
            int r = a - q * b;
            a = b;
            b = r;
        }
        return a;
    }

    public static long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        while (b > 0) {
            long q = a / b;
            long r = a - q * b;
            a = b;
            b = r;
        }
        return a;
    }

    // as + bt = gcd(a, b) -> return s
    public static long xgcd(long a, long b) {
        long r1 = a;
        long r2 = b;
        long s1 = 1;
        long s2 = 0;
        long t1 = 0;
        long t2 = 1;
        while (true) {
            long q = r1 / r2;
            long r = r1 - q * r2;
            long s = s1 - q * s2;
            long t = t1 - q * t2;
            if (r == 0) {
                return s2;
            }
            r1 = r2;
            r2 = r;
            s1 = s2;
            s2 = s;
            t1 = t2;
            t2 = t;
        }
    }
}

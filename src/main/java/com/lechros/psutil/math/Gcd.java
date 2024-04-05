package com.lechros.psutil.math;

public class Gcd {
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        if (b == 0) return a;
        b = Math.abs(b);
        if (a == 0) return b;

        final int aZeros = Integer.numberOfTrailingZeros(a);
        final int bZeros = Integer.numberOfTrailingZeros(b);
        a >>>= aZeros;
        b >>>= bZeros;

        final int t = (Math.min(aZeros, bZeros));

        while (a != b) {
            if ((a + 0x80000000) > (b + 0x80000000)) {
                a -= b;
                a >>>= Integer.numberOfTrailingZeros(a);
            } else {
                b -= a;
                b >>>= Integer.numberOfTrailingZeros(b);
            }
        }
        return a << t;
    }

    public static long gcd(long a, long b) {
        a = Math.abs(a);
        if (b == 0) return a;
        b = Math.abs(b);
        if (a == 0) return b;

        final int aZeros = Long.numberOfTrailingZeros(a);
        final int bZeros = Long.numberOfTrailingZeros(b);
        a >>>= aZeros;
        b >>>= bZeros;

        final int t = (Math.min(aZeros, bZeros));

        while (a != b) {
            if ((a + 0x8000000000000000L) > (b + 0x8000000000000000L)) {
                a -= b;
                a >>>= Long.numberOfTrailingZeros(a);
            } else {
                b -= a;
                b >>>= Long.numberOfTrailingZeros(b);
            }
        }
        return a << t;
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

package com.lechros.psutil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PWriter {
    private final BufferedWriter out;

    public PWriter(OutputStream out) {
        this.out = new BufferedWriter(new OutputStreamWriter(out));
    }

    public void flush() {
        try {
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(char c) {
        try {
            out.write(c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(int i) {
        print(i + "");
    }

    public void print(long l) {
        print(l + "");
    }

    public void print(float f) {
        print(f + "");
    }

    public void print(double d) {
        print(d + "");
    }

    public void print(Object o) {
        print(o + "");
    }

    public void print(String s) {
        try {
            out.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void println() {
        print('\n');
    }

    public void println(char c) {
        print(c);
        print('\n');
    }

    public void println(int i) {
        print(i + "\n");
    }

    public void println(long l) {
        print(l + "\n");
    }

    public void println(float f) {
        print(f + "\n");
    }

    public void println(double d) {
        print(d + "\n");
    }

    public void println(Object o) {
        print(o + "\n");
    }

    public void println(String s) {
        print(s);
        print('\n');
    }

    public void printf(String format, Object... args) {
        print(String.format(format, args));
    }
}

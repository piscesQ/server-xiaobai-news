package com.qingdu.xiaobai.news.demo;

/**
 * author: YanBin
 * date: 2019-11-20
 * desc: 按位或 按位与
 */
public class BitAndOr {
    public static void main(String[] args) {
        int a = 1999;
        int b = 0x10000;
        int c = 0xFFFF;
        int d = 0xF0000;

        int e = 1999 | 0x10000;
        int f = 1999 | 0x20000;
        int g = 1999 | 0x30000;
        int h = 1999 | 0x40000;

        System.out.println("a | b = " + (a | b));
        System.out.println("a & b = " + (a & b));

        System.out.println("a | c = " + (a | c));
        System.out.println("a & c = " + (a & c));

        System.out.println("a | d = " + (a | d));
        System.out.println("a & d = " + (a & d));

        System.out.println("e & c = " + (e & c));
        System.out.println("e >> 16 & c = " + (e >> 16 & c));

        System.out.println("f & c = " + (f & c));
        System.out.println("f >> 16 & c = " + (f >> 16 & c));

        System.out.println("g & c = " + (g & c));
        System.out.println("g >> 16 & c = " + (g >> 16 & c));

        System.out.println("h & c = " + (h & c));
        System.out.println("h >> 16 & c = " + (h >> 16 & c));


    }
}

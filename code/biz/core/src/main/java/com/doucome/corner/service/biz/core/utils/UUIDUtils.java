package com.doucome.corner.service.biz.core.utils;

import java.util.UUID;

public class UUIDUtils {

    private static final int LEN20  = 20;
    private static final int LEN10  = 10;

    /* 随机种子 */
    private static char      X36S[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static char      X10S[] = "0123456789".toCharArray();
 
    /**
     * 根据李战园专利算法生成20位大写UUID
     * 
     * @return 20位UUID
     */
    public static String random20() {
        char chs[] = new char[LEN20];

        /**
         * 生成前8位随机字符(以系统时间为随机池, 以36位数字+英文字母为随机种子)
         */
        long v = (System.currentTimeMillis() - 936748800000L) >> 1; // 1999-9-9
        // 开始，起码要横跨102年:)
        for (int i = 7; i > 0; i--) {
            chs[i] = X36S[(int) (v % 36)];
            v = v / 36;
        }
        chs[0] = X36S[(int) (v % 26) + 10]; // 确保第一个随机字符是"字母", 以符合一般编程的标识符定义

        /**
         * 生成后12位随机字符(以UUID为随机池, 以36位数字+英文字母为随机种子)
         */
        UUID u = UUID.randomUUID();
        v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
        if (v < 0) {
            v = -v;
        }

        for (int i = 8; i < LEN20; i++) {
            chs[i] = X36S[(int) (v % 36)];
            v = v / 36;
        }

        return new String(chs).toLowerCase();
    }
    
    public static String random10(){
        char chs[] = new char[LEN10];
        /**
         * 生成后12位随机字符(以UUID为随机池, 以36位数字+英文字母为随机种子)
         */
        UUID u = UUID.randomUUID();
        long v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
        if (v < 0) {
            v = -v;
        }

        for (int i = 0; i < LEN10; i++) {
            chs[i] = X36S[(int) (v % 36)];
            v = v / 36;
        }
        
        return new String(chs).toLowerCase();
    }

    public static String random20Num() {
        char chs[] = new char[LEN20];
        /**
         * 生成前5位随机字符(以系统时间为随机池, 以10位数字为随机种子)
         */
        long v = (System.currentTimeMillis() - 936748800000L) >> 1; // 1999-9-9
        for (int i = 5; i >= 0; i--) {
            chs[i] = X10S[(int) (v % 10)];
            v = v / 10;
        }

        /**
         * 生成后15位随机字符(以UUID为随机池, 以10位数字为随机种子)
         */
        UUID u = UUID.randomUUID();
        v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
        if (v < 0) {
            v = -v;
        }

        for (int i = 6; i < LEN20; i++) {
            chs[i] = X10S[(int) (v % 10)];
            v = v / 10;
        }

        return new String(chs);
    }
}

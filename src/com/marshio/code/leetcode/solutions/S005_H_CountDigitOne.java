package com.marshio.code.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author masuo
 * @data 10/5/2022 下午3:36
 * @Description 数字 1 的个数
 */

public class S005_H_CountDigitOne {

    // 超时：824883294
    public int countDigitOneI(int n) {
        int times = 0;
        for (int i = n; i >= 0; i--) {
            times += numberOfOne(i + "");
        }
        return times;
    }

    private int numberOfOne(String s) {
        int time = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '1') {
                time++;
            }
        }
        return time;
    }

    // 第二版：记忆 + HashMap，因为 0 <= n <= 10^9  超时
    public int countDigitOneII(int n) {
        int sum = 0;
        HashMap<String, Integer> timesMap = new HashMap<>(n);
        for (int i = 0; i <= n; i++) {
            // 转换成string
            String s = i + "";
            int len = s.length();
            if (len > 1) {
                // len = 2,3,4,,,10
                // 首位数字,
                String prefix = s.substring(0, 1);
                // 后缀 ，需要特殊考虑类似 000 的后缀
                String suffix = s.substring(1);
                Integer t = timesMap.get(Integer.parseInt(suffix) + "");
                if (prefix.equals("1")) {
                    timesMap.put(s, t + 1);
                } else {
                    timesMap.put(s, t);
                }
                sum = sum + timesMap.get(s);
            } else if (s.equals("1")) {
                // len <= 1
                timesMap.put(s, 1);
                sum++;
            } else {
                timesMap.put(s, 0);
            }
        }
        return sum;
    }

    // 第三版：找规律
    /*
     * 各位数为1：每10个数出现一次
     * 十位数为1：每100个数出现一次
     * 百位数为1：每1000个数出现一次
     * 。。。
     *
     * 所以答案就是：
     * 个位数为 1 的次数 +
     * 十位数为 1 的次数 +
     * 百位数为 1 的次数 + 。。。
     *
     * 比如：
     */
    public int countDigitOneIII(int n) {
        // 位数，总和
        int digit = 1, sum = 0;
        // high：高位 ， cur：  ，low：低位
        int high = n / 10, cur = n % 10, low = 0;
        while (high != 0 || cur != 0) {

            if (cur == 0) {
                sum += high * digit;
            } else if (cur == 1) {
                sum += high * digit + low + 1;
            } else {
                sum += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return sum;
    }

    // dp java.lang.OutOfMemoryError: Java heap space
    public int countDigitOneIIII(int n) {
        int sum = 0;
        int[] dp = new int[n];
        for (int i = 0; i < 10; i++) {
            dp[i] = 0;
        }
        dp[1] = 1;
        for (int i = 10; i <= n; i++) {
            dp[i] = dp[i / 10] + dp[i % 10];
            sum += dp[i];
        }
        return sum;
    }

    //
    public int countDigitOneV(int n) {
        int sum = 0;

        // 个位为1：1，11，21，31，41，51，。。。每十个数出现一次
        // 十位为1：10，11，12，13...19 -- 110，111，112，113...119 -- 210,211,212...219,每一百个数出现10次
        // 百位为1：100，101，102...199 -- 1100,1101,1102...1199 -- 21xx -- 31xx -- 91xx,每一千个数出现100次
        // 。。。

        // 综上，求1的个数，就是小于等于这个数的集合中有多少个10，有多少个100，有多少个1000.。。取决于它的最高位是多少

        // 示例：n = 21 、 100 、 101 、 99 、 9
        // 求一个数的位数，比如1000的位数为4，500的位数为3
        int digit = (n + "").length();
        for (int i = 1; i < digit; i++) {
            // 按位求和，i=1时，求个位为的和
            sum += (n / (10 * i)) * 10;
            // 求余数
            int rest = n % (digit * 10);
        }
        return sum;
    }

    // https://www.bilibili.com/video/BV1uJ411573j?spm_id_from=333.337.search-card.all.click
    public int countDigitOneVI(int n) {
        // 示例：123451
        // 12345 [1]
        // 个位为 1 ：前五位有 （0...12345）= 12346 种可能，后面没有数，默认有一种可能,但是12345需要特殊考虑
        // 1234 [1] 6
        // 十位为 1 ：前四位有 （0...1234） = 1235 种可能，后面有 （0...9） = 10 种可能，但是1234需要特殊考虑
        // 123 [1] 51
        // 百位为 1 ：前三位有 （0...123） = 124 种可能，后面有 （0...99） = 100 种可能，但是123需要特殊考虑
        // 。。。。
        String s = String.valueOf(n);
        int length = s.length();
        if (length == 1) {
            return n > 0 ? 1 : 0;
        }
        // 计算第 i 位前缀代表的数值，和后缀代表的数值
        // 例如 abcde 则有 ps[2] = ab; ss[2] = de
        // 前缀值
        int[] preNum = new int[length];
        // 后缀值
        int[] sufNum = new int[length];
        // 个位数时，后缀为 1
        sufNum[0] = Integer.parseInt(s.substring(1));
        for (int i = 1; i < length - 1; i++) {
            preNum[i] = Integer.parseInt(s.substring(0, i));
            sufNum[i] = Integer.parseInt(s.substring(i + 1));
        }
        preNum[length - 1] = Integer.parseInt(s.substring(0, length - 1));
        // 分情况讨论
        int sum = 0;
        for (int i = 0; i < length; i++) {
            // x 为当前位数值，len 为当前位后面长度为多少
            int x = s.charAt(i) - '0', len = length - i - 1;
            int prefix = preNum[i], suffix = sufNum[i];
            int tot = 0;
            tot += prefix * Math.pow(10, len);
            if (x == 1) {
                tot += suffix + 1;
            } else if (x > 1) {
                tot += Math.pow(10, len);
            }
            sum += tot;
        }
        return sum;
    }

    // n^m 求n的m次方
    private int pow(int n, int m) {
        if (m == 0) {
            return 1;
        }
        for (int i = 0; i < m; i++) {
            n = n * n;
        }
        return n;
    }

    @Test
    public void test() {
        // System.out.println(countDigitOneIII(100));
        System.out.println(countDigitOneVI(5));
    }
}

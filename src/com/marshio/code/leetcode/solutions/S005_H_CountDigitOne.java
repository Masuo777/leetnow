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
        for (int i = 10; i >= 0; i--) {
            dp[i] = dp[i - 10] + 1;
            sum += dp[i];
        }
        return sum;
    }

    @Test
    public void test() {
        // System.out.println(countDigitOneIII(100));
        System.out.println(countDigitOneIII(824883294));
    }
}

package com.marshio.code.interview.solutions;

import java.util.Scanner;

/**
 * @author masuo
 * @data 10/5/2022 下午3:23
 * @Description 轻而易举 E02 -- 查字符串个数
 * 给定一个字符串 s 和一个字符串 t，计算在 s 的子序列中 t 出现的个数。
 */

public class QEYJE02Test {

    // 输入：s = “rabbbit”，t = “rabbit”
    // 输出：3  bb的位置依次为：12、13、23
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 字符串 s
        System.out.println("输入第一个字符：");
        String s = scan.nextLine();
        // 字符串 t
        System.out.println("输入第二个字符");
        String t = scan.nextLine();
        long start = System.currentTimeMillis();
        int times = findTnumInS(s, t);
        long end = System.currentTimeMillis();
        System.out.println("出现次数为：" + times + ",耗时：" + (end - start) + "ms");
        System.out.println("Press 'q + enter' to quit.");
        String next = scan.next();
        while (!next.equals("q")) {
            next = scan.next();
        }
        System.exit(0);
    }

    private static int findTnumInS(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        // 动态规划，dp[i][j]代表在 【s以i为起点的子序列】【 t以j为起点的子序列】的公共部分
        int[][] dp = new int[m + 1][n + 1];
        // i == 0 s[0] = "" 对于任意的 t[*] 都满足
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}

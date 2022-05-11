package com.marshio.code.interview.solutions;

import java.util.Scanner;

/**
 * @author masuo
 * @data 10/5/2022 下午3:23
 * @Description 轻而易举 E01 -- 查数字个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 */

public class QEYJE01Test {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("输入整数 n：");
        int n = scan.nextInt();
        // 数字
        int times = findOneTimeI(n);
        System.out.println("出现次数：" + times);
        System.out.println("Press any key to out.");
        while (scan.next() != null){
            System.exit(0);
        }
    }

    private static int findOneTimeI(int n) {
        int times = 0;
        for (int i = n; i >= 0; i--) {
            times += numberOfOne(i + "");
        }
        return times;
    }

    private static int numberOfOne(String s) {
        int time = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '1') {
                time++;
            }
        }
        return time;
    }

}

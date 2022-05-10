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
        int n = scan.nextInt();
        int times = findOneTime(n);
        System.out.println(times);
    }

    private static int findOneTime(int n) {
        int times = 0;
        for (int i = n; i >= 0; i--) {
            times += numberOfOne(i + "");
        }
        return times;
    }

    private static int numberOfOne(String s) {
        int time = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '1') {
                time++;
            }
        }
        return time;
    }
}

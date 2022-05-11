package com.marshio.code.interview.solutions;

import java.util.Scanner;

/**
 * @author masuo
 * @data 10/5/2022 下午3:23
 * @Description 轻而易举 E01 -- 层板等分衣柜
 * 给定一个高度为 2000mm 的柜子空间，以及 n 个层板距离柜子底部高度，满足移动层板位置使得层板等分衣柜的空间。计算所有移动层板的顺序。
 * 层板号自下向上依次排列，1，2..n。层板需要考虑空间位置，不能跨层板移动。
 */

public class QEYJF01Test {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        String s = scan.nextLine();
        String[] array = s.split(",");
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

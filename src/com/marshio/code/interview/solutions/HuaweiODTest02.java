package com.marshio.code.interview.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author masuo
 * @date: 2022/05/12/ 下午7:51
 * @description
 */
public class HuaweiODTest02 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();

        int k = scan.nextInt();

        if(str.length() < k || k > 26 || k == 0) {
            System.out.println(-1);
            System.exit(0);
        }
        // 数组 26个字母
        HashMap<Character, Integer> lxlen = new HashMap<>(26);
        int len = 0;
        int lastIndex = -1;
        for (int i = 0; i < str.length(); i++) {
            // 下标
            int index = str.charAt(i);
            if (i == 0) {
                lastIndex = index;
                len++;
                continue;
            }
            if (lastIndex == index) {
                // 连续
                len++;
            } else {
                // 不连续，len重置为 1

                // 判断是否存在这个字符
                Character c = (char) lastIndex;
                if (lxlen.containsKey(c)) {
                    lxlen.put(c, Math.max(len, lxlen.get(c)));
                } else {
                    lxlen.put(c, len);
                }
                len = 1;
            }
            lastIndex = index;
        }
        // 最后一条数据
        Character c = (char) lastIndex;
        if (lxlen.containsKey(c)) {
            lxlen.put(c, Math.max(len, lxlen.get(c)));
        } else {
            lxlen.put(c, len);
        }

        int[] nums = new int[26];
        lxlen.forEach((key, i) -> {
            int index = key - 'A';
            nums[index] = i;
        });

        int[] sorted = Arrays.stream(nums).sorted().toArray();

        System.out.println(sorted[26 - k] == 0 ? -1:sorted[26 - k]);
    }
}

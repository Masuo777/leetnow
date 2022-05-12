package com.marshio.code.interview.solutions;

import java.util.*;

/**
 * @author masuo
 * @date: 2022/05/12/ 下午7:19
 * @description
 */
public class HuaweiODTest01 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 输入第一行
        int i = Integer.parseInt(scan.nextLine());
        String[] s = scan.nextLine().split(" ");
        if(s.length == 1){
            System.out.println(1);
            System.exit(0);
        }
        int[] nums = new int[s.length];
        for (int j = 0; j < s.length; j++) {
            nums[j] = Integer.parseInt(s[j]);
        }

        // 排序
        nums = Arrays.stream(nums).sorted().toArray();

        // 有1 只需要一种颜色
        if(nums[0] == 1) {
            System.out.println(1);
        }

        Set<Integer> set = new HashSet<>();
        //
        for (int num : nums) {
            if(set.isEmpty()) {
                set.add(num);
                continue;
            }
            int index = 0;
            for (Integer next : set) {
                index++;
                if (num % next == 0) {
                    break;
                }else if(index < set.size()){
                    continue;
                }
                set.add(num);
            }
        }
        System.out.println(set.size());
    }
}

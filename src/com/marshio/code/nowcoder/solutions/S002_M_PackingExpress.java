package com.marshio.code.nowcoder.solutions;

import java.util.*;

/**
 * @author masuo
 * @data 12/5/2022 上午11:05
 * @Description 包装快递 -- 典型的背包问题，dp
 * 计算最多能装多少个快递
 * 注：快递的体积不受限制，快递数最多1000个，货车载重最大50000。不需要考虑异常输入。
 */

public class S002_M_PackingExpress {

    // 第一行输入每个快递的重量，用英文逗号分隔，如：5,10,2,11
    // 第二行输入货车的载重量，如：20
    // 输出最多能装多少个快递，如：3

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 求重量分组
        String[] s = scan.nextLine().split(",");
        int maxWeight = scan.nextInt();
        int[] weight = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            weight[i] = Integer.parseInt(s[i]);
        }

        // 对weight排序
        weight = Arrays.stream(weight).sorted().toArray();

        int index = 0;
        for (int i : weight) {
            if(maxWeight - i > 0) {
                // 能装下
                index++;
                maxWeight-=i;
            }
        }
        System.out.println(index);
    }

}

package com.marshio.code.nowcoder.solutions;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author masuo
 * @data 12/5/2022 下午3:28
 * @Description 考勤
 * 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下：
 * 缺勤不超过一次；没有连续的迟到/早退；任意连续7次考勤，缺勤/迟到/早退不超过3次
 */

public class S004_M_Attendance {

    //输入描述:
    // 1、用户的考勤数据字符串，记录条数 >= 1；
    // 2、输入字符串长度<10000；不存在非法输入
    public static void main(String[] args) {
        // absent：缺勤
        // late：迟到
        // leaveearly：早退
        // present：正常上班

        Scanner scan = new Scanner(System.in);
        // 记录条数
        int record = scan.nextInt();
        List<String> list = new ArrayList<>();
        while (record-- > 0) {
            Collections.addAll(list, scan.nextLine().split(" "));
        }

        AtomicBoolean flag = new AtomicBoolean(true);
        AtomicInteger absent = new AtomicInteger(0);
        // effectively final
        list.forEach(s -> {
            // lambda表达式同线程，匿名类，内部类一样，无法调用外部常量，想要调用的话需要用final修饰
            // lambda不支持局部变量，因为lambda在另外一个线程中执行，且是延迟加载的，运行的时候有可能这个变量已经丢失（调用lambda的线程已执行完毕）
            // 所以我们需要加final修饰，被final修饰的变量属于
            // 加final就是将局部变量复制一份到lambda执行线程，

            // 如果我们要修改这个值，那么我们可以使用Automic类型，其实就是包装，改变引用的值，不改变地址，且修改是原子操作
            switch(s) {
                case "absent":
                    // 缺勤不超过一次
                    int i = absent.incrementAndGet();
                    if(i >= 1) {
                        flag.set(false);
                    }
                    break;
                case "late":
                    // 没有连续的迟到/早退
                    flag.set(false);
                    break;
                case "leaveearly":
                    flag.set(false);
                    break;
                case "present":
                    // 任意连续7次考勤，缺勤/迟到/早退不超过3次
                    break;
            }
        });
    }
}

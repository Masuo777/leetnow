package com.marshio.code.nowcoder.solutions;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author masuo
 * @data 12/5/2022 上午11:05
 * @Description TLV 解码：Tag Length Value
 * Tag：     信元标识，不重复，1字节
 * Length：  信元Value的长度，2字节
 * Value：   信元的值
 */

public class S003_M_TLVDecoding {

    // 第一行输入一个字符串，表示待解码信元的Tag,如 31
    // 第二行输入一个字符串，表示待解码的16进制码流，字节之间用空格分隔，如 32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC
    //      输出一个字符串，表示待解码信元以16进制表示的Value，如 32 33

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Tag
        String tag = scan.nextLine();
        // 16进制码流，以某信元的Tag开头，字节之间用空格分隔
        String[] s = scan.nextLine().split(" ");
        int index = 0;
        String value = "";
        while (index < s.length) {
            // 获取1字节的信元Tag
            String t = s[index++];
            // 获取2字节的长度，小端序转化为十进制
            int len = Integer.parseInt(s[index + 1] + s[index], 16);
            index += 2;
            if (!t.equals(tag)) {
                // tag 不相等
                index += len;
                continue;
            }
            StringBuilder sb = new StringBuilder(len);
            while (len > 0) {
                sb.append(s[index++]).append(" ");
                len--;
            }
            value = sb.deleteCharAt(sb.length() - 1).toString();
            break;
        }

        System.out.println(value);
    }

}

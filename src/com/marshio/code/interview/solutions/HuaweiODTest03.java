package com.marshio.code.interview.solutions;

import java.util.Scanner;

/**
 * @author masuo
 * @date: 2022/05/12/ 下午8:30
 * @description
 */
public class HuaweiODTest03 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        String[] nums = s.replace("[", "").replace("]", "").split(",");
        if(nums.length == 1) {
            System.out.println(0);
            System.exit(0);
        }
        if(nums.length == 2) {
            System.out.println(1);
            System.exit(0);
        }
        int[][] books = new int[nums.length / 2][2];
        for (int i = 0; i < nums.length; i += 2) {
            books[i / 2][0] = Integer.parseInt(nums[i]);
            books[i / 2][1] = Integer.parseInt(nums[i + 1]);
        }


        // 给长[0]排序，快
        quickSort(books, 0, books.length - 1);
        // 给宽[1]排序,稳定
        bubbleSort(books);

        //for (int[] book : books) {
        //    System.out.println(book[0] + " " + book[1]);
        //}

        // 从倒数第二个开始
        int sum = 1;
        int last = books.length - 1;
        for (int i = books.length - 2; i >= 0; i--) {
            if(books[i][0] < books[last][0] && books[i][1] < books[last][1]) {
                last = i;
                sum++;
            }
        }
        // 75 + 70 + 160 =
        System.out.println(sum);
    }

    private static void bubbleSort(int[][] books) {
        for (int i = books.length; i > 0; i--) {
            for (int j = 0; j < books.length - 1; j++) {
                if (books[j][1] > books[j + 1][1]) {
                    swap(books, j, j + 1);
                }
            }
        }
    }

    private static void quickSort(int[][] books, int L, int R) {
        if (L < R) {
            int[] p = partition(books, L, R);
            quickSort(books, L, p[0] - 1); // <= 区
            quickSort(books, p[1] + 1, R); // > 区
        }
    }

    private static int[] partition(int[][] books, int l, int r) {
        // 取最右侧为基准,3 5 6 7 4 3 5 8
        int pivot = books[r][0];
        // 小于区的起始点为 l-1
        int indexL = l - 1;
        // 数组起点为l
        int index = l;
        while (index <= r) {
            // sorted[index] 和 pivot 对比， 两种情况
            if (books[index][0] <= pivot) {
                // 情况1：小于等于基准值，则交换当前位置数字和小于等于区的下一个数
                swap(books, index, ++indexL);
            }
            // 情况2：大于基准值，直接index+1
            index++;
        }
        return new int[]{indexL, indexL};
    }

    private static void swap(int[][] books, int index, int i) {
        int[] tmp = books[index];
        books[index] = books[i];
        books[i] = tmp;
    }
}

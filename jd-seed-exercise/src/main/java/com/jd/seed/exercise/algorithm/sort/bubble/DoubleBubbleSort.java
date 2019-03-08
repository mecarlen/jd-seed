package com.jd.seed.exercise.algorithm.sort.bubble;

public class DoubleBubbleSort {
    public static void main(String[] args) {
        long staruTime = System.currentTimeMillis();
        int[] num = new int[3000000];
        for (int i = 0; i < num.length; i++)
            num[i] = (int) (Math.random() * 100);
//        for (int n : num)
//            System.out.print(n + "\t");
        System.out.println();
        sort(num, 0, num.length);
//        for (int i : num)
//            System.out.print(i + "\t");
        long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println((endTime - staruTime) + "ms");
    }

    public static void sort(int[] num, int l, int r) {
        if (l < r) {
            int i = l;
            int j = l + 1;
            while (j < r) {
                if (num[j] < num[l]) {
                    i++;
                    int temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
//                    num[i] = num[i] + num[j];
//                    num[j] = num[i] - num[j];
//                    num[i] = num[i] - num[j];
//                    num[i] =num[i] ^ num[j] ^ (num[j] = num[i]);
                }
                j++;
            }
            int temp = num[i];
            num[i] = num[l];
            num[l] = temp;
            sort(num, l, i);
            sort(num, i + 1, r);
        }
    }
}
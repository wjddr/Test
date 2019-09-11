package com.dou;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] data0 = new int[]{1, 2, 3};
        int[] tmp = data0.clone();
        tmp[0] = 11;
        for(int x = 0; x < data0.length; x++){
            System.out.println("data0" + x + ": " +data0[x]);
        }
        int[] data1 = new int[]{4, 5, 6};
        data0 = data1;
        for(int x = 0; x < data0.length; x++){
            System.out.println("data0" + x + ": " +data0[x]);
        }
        for(int x = 0; x < tmp.length; x++){
            System.out.println("tmp" + x + ": " +tmp[x]);
        }
    }
}

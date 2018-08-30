package com.wpc.test.algorithm;

import com.wpc.common.utils.date.ChineseCalendar;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Test {

    public static void main(String[] args) {
        int[] a = In.readInts("C:\\Users\\admin\\Desktop\\algs4-data\\2Kints.txt");
        Stopwatch watch = new Stopwatch();
        int count = count(a);
        double time = watch.elapsedTime();
        System.out.println(count + "个" + time + "秒");
    }

    public static int count(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        count++;
        return count;
    }

}

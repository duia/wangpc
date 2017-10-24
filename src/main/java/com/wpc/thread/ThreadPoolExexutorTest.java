package com.wpc.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExexutorTest {

    private static int count = 10000;

    public static void main(String[] args) {
        testPool();
        testNoPool();
    }

    public static void testPool() {
        long startTime = System.currentTimeMillis();
        final List<Integer> list = new LinkedList<>();
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(count));
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            tp.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        tp.shutdown();
        try {
            tp.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(list.size());
    }

    public static void testNoPool() {
        long startTime = System.currentTimeMillis();
        final List<Integer> list = new LinkedList<>();
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(list.size());
    }

}

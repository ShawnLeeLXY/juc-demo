package com.lxy.helper;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static final int NUMBER = 10;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () ->
                System.out.println("10个线程都已结束！")
        );
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号线程结束");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }

}

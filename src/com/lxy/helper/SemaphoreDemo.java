package com.lxy.helper;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 6个线程抢3个执行机会
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 设置许可量为5
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    // 抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println("---" + Thread.currentThread().getName() + "释放了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}

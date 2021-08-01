package com.lxy.itc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {

    // 定义标志位
    private int flag = 1;
    // 创建Lock锁
    private Lock lock = new ReentrantLock();
    // 创建三个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 打印1次，参数第loop轮
    public void print1(int loop) {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                // 等待
                c1.await();
            }
            // 执行
            System.out.println(Thread.currentThread().getName() + ": " + 1 + "，第" + loop + "轮");
            flag = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public void print2(int loop) {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 1; i <= 2; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i + "，第" + loop + "轮");
            }
            flag = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print3(int loop) {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i + "，第" + loop + "轮");
            }
            flag = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class ThreadDemo3 {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareResource.print1(i);
            }
        }, "thread-A").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareResource.print2(i);
            }
        }, "thread-B").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareResource.print3(i);
            }
        }, "thread-C").start();
    }

}

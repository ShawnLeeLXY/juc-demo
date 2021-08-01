package com.lxy.itc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + ": " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + ": " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-A").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-B").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-C").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-D").start();
    }
}

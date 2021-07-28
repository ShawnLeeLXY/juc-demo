package com.lxy.ticket;

import java.util.concurrent.locks.ReentrantLock;

public class SellTicketLock {

    static class Ticket {
        // 票数
        private int number = 30;

        // 创建可重入锁
        private final ReentrantLock lock = new ReentrantLock();

        // 卖票方法
        public void sell() {
            // 上锁
            lock.lock();
            try {
                // 判断是否有票可卖
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出了一张票，还剩下" + (--number) + "张票");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 解锁
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        // 创建资源类
        Ticket ticket = new Ticket();
        // 创建三个线程
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 40; j++) {
                    ticket.sell();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "thread" + i).start();
        }
    }

}

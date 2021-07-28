package com.lxy.ticket;

public class SellTicket {

    static class Ticket {

        // 票数
        private int number = 30;

        // 操作方法：卖票
        public synchronized void sell() {
            // 判断是否有票 有则卖一张票
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了一张票，还剩下" + (--number) + "张票");
            }
        }

    }

    public static void main(String[] args) {
        // 创建资源类
        Ticket ticket = new Ticket();
        // 创建三个线程
        for (int i = 1; i <= 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 40; i++) {
                        ticket.sell();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "thread" + i).start();
        }

    }

}

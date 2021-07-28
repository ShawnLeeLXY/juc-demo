package com.lxy;

public class DaemonDemo {

    public static void main(String[] args) {
        // Lambda表达式实现Runnable接口
        Thread thread1 = new Thread(() -> {
            // true为守护线程 false为用户线程
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().isDaemon());
            while (true) {
                // 主线程结束 若用户线程还在运行 JVM存活
                // 由于主线程被设为守护线程 故JVM退出
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }, "thread1");
        // 设置守护线程
        thread1.setDaemon(true);
        thread1.start();
        System.out.println(Thread.currentThread().getName() + " is over.");
    }

}

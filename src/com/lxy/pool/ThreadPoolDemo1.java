package com.lxy.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建方式演示
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        // 1池5线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        // 10个业务
        for (int i = 1; i <= 10; i++) {
            threadPool1.execute(() -> System.out.println(Thread.currentThread().getName() + "正在处理业务..."));
        }
        // 关闭线程池 但是既不会强行终止正在执行的任务 也不会取消已经提交的任务
        threadPool1.shutdown();

        // 1池1线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            threadPool2.execute(() -> System.out.println(Thread.currentThread().getName() + "正在处理业务..."));
        }
        threadPool2.shutdown();

        // 可扩容线程池
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            threadPool3.execute(() -> System.out.println(Thread.currentThread().getName() + "正在处理业务..."));
        }
        threadPool3.shutdown();
    }

}

package com.lxy.pool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class ThreadPoolDemo2 {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 2L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i <= 10; i++) {
            threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "正在处理业务..."));
        }
        threadPool.shutdown();
    }

}

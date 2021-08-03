package com.lxy.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable {
    @Override
    public void run() {
        // do nothing
    }
}

class MyThread2 implements Callable {
    @Override
    public Object call() throws Exception {
        return "calling...";
    }
}

public class CallableDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Runnable方式创建线程
        new Thread(new MyThread1(), "thread-runnable").start();

        // Callable方式创建线程
        FutureTask<String> futureTask1 = new FutureTask<>(new MyThread2());
        FutureTask<String> futureTask2 = new FutureTask<>(() -> "calling...");
        FutureTask<Integer> futureTask3 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable.");
            return 666;
        });
        // 创建对应线程
        new Thread(futureTask1, "thread-1").start();
        new Thread(futureTask2, "thread-2").start();
        new Thread(futureTask3, "thread-3").start();
        while (!futureTask3.isDone()) {
            System.out.println("waiting for thread-3 to finish...");
        }
        // 调用FutureTask的get方法 注意可以重复调用
        System.out.println("futureTask1: " + futureTask1.get());
        System.out.println("futureTask2: " + futureTask2.get());
        System.out.println("futureTask3: " + futureTask3.get());
        System.out.println("futureTask3: " + futureTask3.get());
        System.out.println(Thread.currentThread().getName() + " is over.");
    }

}

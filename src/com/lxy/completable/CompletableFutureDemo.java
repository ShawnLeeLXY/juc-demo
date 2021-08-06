package com.lxy.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用和同步调用
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步调用
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() ->
                System.out.println(Thread.currentThread().getName() + ": CompletableFuture1"));
        completableFuture1.get();

        // 异步调用
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": CompletableFuture2");
            return 666;
        });
        completableFuture2.whenComplete((t, u) -> {
            System.out.println("t = " + t);//t为返回值
            System.out.println("u = " + u);//u为异常信息
        }).get();
    }

}

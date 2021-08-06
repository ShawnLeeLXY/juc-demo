package com.lxy.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列演示
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个定长的阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // 再put线程就会阻塞
        //blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // 再take线程就会阻塞
        //System.out.println(blockingQueue.take());

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // offer和poll方法都可以设置超时时间
        // 阻塞超过3s则自动结束线程
        System.out.println(blockingQueue.offer("d", 3L, TimeUnit.SECONDS));
    }

}

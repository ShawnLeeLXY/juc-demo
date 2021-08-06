package com.lxy.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁演示
 */
class MyCache {

    // 共享map资源 往map里读或写
    private volatile Map<String, Object> map = new HashMap<>();

    // 初始化读写锁 向上转型
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "正在写" + key);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "正在读" + key);
        Object res = null;
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            res = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return res;
    }

}


public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        // 5个线程开始写
        for (int i = 1; i <= 5; i++) {
            final String num = String.valueOf(i);
            new Thread(() -> myCache.put(num, num), String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 5个线程开始读
        for (int i = 1; i <= 5; i++) {
            final String num = String.valueOf(i);
            new Thread(() -> System.out.println(myCache.get(num)), String.valueOf(i)).start();
        }
    }

}

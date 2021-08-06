package com.lxy.rw;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写锁降级为读锁演示
 */
public class LockDegrade {

    public static void main(String[] args) {
        // 创建读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        // 锁降级
        writeLock.lock();
        System.out.println("获取了写锁");
        readLock.lock();
        System.out.println("获取了读锁");
        writeLock.unlock();
        System.out.println("释放了写锁");
        readLock.unlock();
        System.out.println("释放了读锁");
    }

}

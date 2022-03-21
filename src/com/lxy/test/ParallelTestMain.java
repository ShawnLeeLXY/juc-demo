package com.lxy.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelTestMain {

    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        List<Thread> list = new LinkedList<>();
        BufferedWriter os = new BufferedWriter(new FileWriter("E:\\Develop\\Java\\source\\parallelTest.txt"));
        int max = 100;
        for (int i = 1; i <= 8; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < max; j++) {
                    if (j % 10 == 0) {
                        String str = Thread.currentThread().getName() + " wrote " + j;
                        lock.lock();
                        try {
                            os.write(str);
                            os.newLine();
                            os.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }, "t-" + i);
            t.start();
            list.add(t);
        }
        for (Thread t : list) {
            t.join();
        }
        os.close();
        System.out.println("The main thread is over!");
    }
}

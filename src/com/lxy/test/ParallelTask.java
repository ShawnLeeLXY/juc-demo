package com.lxy.test;

import java.io.*;

public class ParallelTask implements Runnable {

    private static int max;
    private BufferedWriter os;

    public ParallelTask(BufferedWriter os) {
        this.max = 100;
        this.os = os;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < max; i++) {
                if (i % 10 == 0) {
                    String str = Thread.currentThread().getName() + " wrote " + i + "\r\n";
                    try {
                        os.write(str);
                        os.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + "closing...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

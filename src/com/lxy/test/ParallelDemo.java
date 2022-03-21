package com.lxy.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ParallelDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Thread> list = new LinkedList<>();
        BufferedWriter os = new BufferedWriter(new FileWriter("E:\\Develop\\Java\\source\\parallelTest.txt"));
        for (int i = 1; i <= 8; i++) {
            Thread t = new Thread(new ParallelTask(os), "t-" + i);
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

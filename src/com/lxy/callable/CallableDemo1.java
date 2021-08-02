package com.lxy.callable;

import java.util.concurrent.Callable;

class MyThread1 implements Runnable {
    @Override
    public void run() {

    }
}

class MyThread2 implements Callable {
    @Override
    public Object call() throws Exception {
        return "calling...";
    }
}

public class CallableDemo1 {

    public static void main(String[] args) {
        new Thread(new MyThread1(), "thread-1").start();
        new MyThread2();
    }

}

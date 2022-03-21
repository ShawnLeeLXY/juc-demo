package com.lxy.test.syn;

public class SecondThread implements Runnable {

    private WRFile wr;

    public SecondThread(WRFile wr) {
        this.wr = wr;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            wr.read2();
        }
    }
}

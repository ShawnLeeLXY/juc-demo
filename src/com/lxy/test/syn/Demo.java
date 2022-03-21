package com.lxy.test.syn;

public class Demo {

    public static void main(String[] args) {
        //创建数据对象
        WRFile wr = new WRFile();
        //设置和获取类
        FirstThread ft = new FirstThread(wr);
        SecondThread st = new SecondThread(wr);
        //线程类
        Thread th1 = new Thread(ft);
        Thread th2 = new Thread(st);
        //启动线程
        th1.start();
        th2.start();
    }
}

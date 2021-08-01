package com.lxy.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * List集合线程安全演示解决方案
 */
public class ThreadDemo4 {

    public static void main(String[] args) {
        // 线程不安全
        //List<String> list = new ArrayList<>();

        // 解决方案一：Vector
        //List<String> list = new Vector<>();

        // 解决方案二：Collections
        //List<String> list = Collections.synchronizedList(new ArrayList<>());

        // 解决方案三：CopyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}

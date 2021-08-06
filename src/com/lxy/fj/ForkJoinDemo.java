package com.lxy.fj;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分支合并演示
 */
class MyTask extends RecursiveTask<Integer> {

    // 拆分差值不能超过10
    private static final int VALUE = 10;
    // 拆分开始值
    private int begin;
    // 拆分结束值
    private int end;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    // 拆分与合并过程
    @Override
    protected Integer compute() {
        int res = 0;
        if (end - begin <= VALUE) {
            for (int i = begin; i <= end; i++) {
                res += i;
            }
        } else {
            // 获取中间值
            int mid = (begin + end) / 2;
            // 拆分左边
            MyTask taskLeft = new MyTask(begin, mid);
            // 拆分右边
            MyTask taskRight = new MyTask(mid + 1, end);
            taskLeft.fork();
            taskRight.fork();
            // 合并结果
            res = taskLeft.join() + taskRight.join();
        }
        return res;
    }
}


public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建MyTask对象
        MyTask myTask = new MyTask(0, 100);
        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        // 获取最终合并之后的结果
        Integer res = forkJoinTask.get();
        System.out.println(res);
        // 关闭池对象
        forkJoinPool.shutdown();
    }

}

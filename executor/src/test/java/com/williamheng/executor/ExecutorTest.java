package com.williamheng.executor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    @Test
    public void foobar() throws InterruptedException {
        var max = 1_000;
        var hold = new ArrayList<ExecutorService>();
        for (var i = 0; i < max; i++) {
            var executor = Executors.newScheduledThreadPool(2);
            executor.schedule(() -> {
                System.out.println(Thread.currentThread().getName());
                }, 10 * i, TimeUnit.MILLISECONDS);
            hold.add(executor);
        }

        Thread.sleep(10_000L);
    }
}

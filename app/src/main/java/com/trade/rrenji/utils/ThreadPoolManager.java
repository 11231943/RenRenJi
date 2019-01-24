package com.trade.rrenji.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消息处理线程池
 */
public class ThreadPoolManager {
    private static final ThreadPoolManager manager = new ThreadPoolManager();
    private ExecutorService service;

    private ThreadPoolManager() {
        service = Executors.newCachedThreadPool();
    }

    public static ThreadPoolManager getInstance() {
        return manager;
    }

    /**
     * 添加一个线程到线程池中
     *
     * @param runnable
     */
    public void addTask(Runnable runnable) {
        service.submit(runnable);
    }
}

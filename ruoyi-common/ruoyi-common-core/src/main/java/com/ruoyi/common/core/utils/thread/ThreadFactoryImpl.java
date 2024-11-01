
package com.ruoyi.common.core.utils.thread;


import com.ruoyi.common.core.exception.UtilException;
import com.ruoyi.common.core.utils.StringUtils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description: 线程工厂
 * @author: plover
 * @date: 2023/7/21 12:12
 */
public class ThreadFactoryImpl implements ThreadFactory {

    private final AtomicLong threadIndex = new AtomicLong(0);
    private final String threadNamePrefix;
    private final boolean daemon;

    public ThreadFactoryImpl(final String threadNamePrefix) {
        this(threadNamePrefix, false);
    }

    public ThreadFactoryImpl(final String threadNamePrefix, boolean daemon) {
        this.threadNamePrefix = threadNamePrefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, threadNamePrefix + this.threadIndex.incrementAndGet());
        thread.setDaemon(daemon);

        // Log all uncaught exception
        thread.setUncaughtExceptionHandler((t, e) -> {
            throw new UtilException(StringUtils.format("[BUG] Thread has an uncaught exception, threadId={}, threadName={}", t.getId(),
                    t.getName(), e));});

        return thread;
    }
}

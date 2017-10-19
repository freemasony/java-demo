package com.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoujian on 2016-12-27.
 */
public class ThreadPoolUtil
{

	private ThreadPoolExecutor executor;
	private static final long keepAliveTime = 24;

	public ThreadPoolUtil(int corePoolSize, int maximumPoolSize)
	{
		BlockingQueue queue = new LinkedBlockingQueue();
		this.executor = new ThreadPoolExecutor(corePoolSize,  maximumPoolSize,  keepAliveTime, TimeUnit.HOURS, queue);
	}

	public void execute(Runnable runnable)
	{
		if (this.executor != null)
		{
			this.executor.execute(runnable);
		}
	}

	public void shutdown()
	{
		if (this.executor != null)
		{
			this.executor.shutdown();
		}
	}
}

package com.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2014/8/20.
 */
public class ThreadPoolUtil
{

	private static final int fixPoolSize=10;
	private static final int maximumPoolSize=10;
	private static final int corePoolSize=5;
	public static ThreadPoolExecutor executor;
	private static final long keepAliveTime = 24;
	public static ScheduledExecutorService scheduledExecutorService;
	public static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

	public ThreadPoolUtil() {
		getExecutor();
	}


	static class ThreadPoolFactory implements ThreadFactory {

		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		ThreadPoolFactory() {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() :
					Thread.currentThread().getThreadGroup();
			namePrefix = "threadpool-" +
					poolNumber.getAndIncrement() +
					"-thread-";
		}

		/**
		 * Constructs a new {@code Thread}.  Implementations may also initialize
		 * priority, name, daemon status, {@code ThreadGroup}, etc.
		 *
		 * @param r a runnable to be executed by new thread instance
		 * @return constructed thread, or {@code null} if the request to
		 * create a thread is rejected
		 */
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r,
					namePrefix + threadNumber.getAndIncrement(),
					0);
			if (t.isDaemon())
				t.setDaemon(false);
			if (t.getPriority() != Thread.NORM_PRIORITY)
				t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
	}

	private static ThreadPoolExecutor getExecutor(){
		if(executor==null || executor.isShutdown()){
			synchronized (ThreadFactory.class){
				if(executor==null || executor.isShutdown()){
					executor = new ThreadPoolExecutor(corePoolSize,  maximumPoolSize,  keepAliveTime, TimeUnit.HOURS, new LinkedBlockingQueue(),new ThreadPoolFactory());
					executor.allowCoreThreadTimeOut(false);
				}
			}
		}
		return executor;
	}



	private static ScheduledExecutorService getScheduledExecutor(){
		if(scheduledExecutorService==null || scheduledExecutorService.isShutdown()){
			synchronized (ThreadFactory.class){
				if(scheduledExecutorService==null || scheduledExecutorService.isShutdown()){
					scheduledExecutorService = Executors.newScheduledThreadPool(fixPoolSize);
				}
			}
		}
		return scheduledExecutorService;
	}


	public static void schedule(Runnable command,
								long delay, TimeUnit unit)
	{
		getScheduledExecutor().schedule(command,delay,unit);
	}

	public static void execute(Runnable runnable)
	{
			getExecutor().execute(runnable);
	}

	public static void shutdown()
	{
		if (executor != null)
		{
			executor.shutdown();
		}
	}
}

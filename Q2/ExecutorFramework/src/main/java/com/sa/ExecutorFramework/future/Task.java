package com.sa.ExecutorFramework.future;

/**
 * A task that returns a result and may throw an exception.
 * The Task interface is similar to Callable/Runnable, in that both 
 * are designed for classes whose instances are potentially executed 
 * by another thread
 * 
 * @author karan
 * @param <T>
 */
public interface Task<T> {
	
	public T call() throws Exception;
 
}

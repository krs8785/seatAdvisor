package com.sa.ExecutorFramework.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sa.ExecutorFramework.future.Task;
import com.sa.ExecutorFramework.future.TaskFuture;
import com.sa.ExecutorFramework.thread.WorkerThread;


/**
 * The Executor allows to create N worker threads that will parallelly execute the 
 * tasks. It creates a thread pool using a blockingQueue to which the task are added. 
 * The thread picks up the task and executes them, if queue is empty thread will wait.
 * 
 * @author karan
 *
 */
public class Executor {
	
	private final BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();
	private final Object POISON_PILL = new Object();

	private int numThreads;
	/**
	 * Constructor: 
	 * Create N worker threads.
	 * 
	 * @param numThreads
	 */
	public Executor(int numThreads) {
		setNumThreads(numThreads);
		for (int i=0 ; i<numThreads ; i++) {
			WorkerThread worker = new WorkerThread(queue, POISON_PILL);
			worker.start();
		}
	}

	/**
	* This method allows to submit task interface to be implemented. Return a future
	* object that will track the progress of the task.
	* 
	* @param task
	* @return
	* @throws InterruptedException
	*/
	public <T> TaskFuture<T> submit(Task<T> task) throws InterruptedException {
		if (task == null) {
			throw new NullPointerException();
		}
		TaskFuture<T> future = new TaskFuture<T>(task);
		queue.put(future);
		return future;
	}

	/**
	* Add a POSION object into the queue to kill the waiting thread if no
	* more task are available
	*/
	public void shutDown() {
		for (int i=0; i < getNumThreads(); i++) {
		    queue.add(POISON_PILL);
		}
	}

	public int getNumThreads() {
		return numThreads;
	}

	public void setNumThreads(int numThreads) {
		this.numThreads = numThreads;
	}

}

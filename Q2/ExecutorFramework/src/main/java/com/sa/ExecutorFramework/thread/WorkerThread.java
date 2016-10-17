package com.sa.ExecutorFramework.thread;

import java.util.concurrent.BlockingQueue;

import com.sa.ExecutorFramework.future.TaskFuture;

/**
 * Worker thread executes the task in the queue. Worker thread
 * wait for blockingQueue to contain task, to kill/interrupt the 
 * the thread on poison object.
 * 
 * @author karan
 *
 */
public class WorkerThread extends Thread {

	private BlockingQueue<Object> queue;

	private Object POISON_PILL;
	private boolean flag = true;
	
	public WorkerThread(BlockingQueue<Object> queue, Object POISON_PILL) {
		this.queue = queue;
		this.POISON_PILL = POISON_PILL;
	}
	
	/**
	 * Run method queries task from the queue and executes it. 
	 * If a poison object is found it stops the thread. (used for shutdown in executor)
	 */
	public void run() {
		while(flag) {
			try {
				Object obj = queue.take();
				if(obj == POISON_PILL){
					break;
				}
				if(obj instanceof TaskFuture) {
					((TaskFuture<?>) obj).run();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}

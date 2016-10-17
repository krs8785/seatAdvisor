package com.sa.ExecutorFramework.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * This class replicates the functionality of Future with methods to query to see 
 * if the computation is complete, and retrieve the result of the computation. TaskFuture
 * can be used to wrap a Task object. 
 *   
 * @author karan
 *
 * @param <T>
 */
public class TaskFuture <T> {

	private Task<T> task;
	private T result;
	private boolean isRunning;
	public enum State {
		WAIT,COMPLETED,FAILED
	}
	private State state;
	private Throwable exception;
	
	/**
	 * Creates a TaskFuture that will, upon running, execute the given Task.
	 * 
	 * @param task task interface
	 * @throws NullPointerException if task is null
	 */
	public TaskFuture(Task<T> task) {
		if (task == null) {
    		throw new NullPointerException();
    	}
		setTask(task);
	}

	/**
	 * Runs the TaskFuture and performs the computation
	 */
	public void run() {
		setRunning(true);
		try{
			setResult(getTask().call());
		}catch(Throwable ex){
			setException(ex);
		}
		setRunning(false);
	}
	
	/**
	 * The isDone return a boolean depending on whether the task has finished execution
	 * @return
	 */
	public boolean isDone() {
		if(getState()!=null && 
				(getState().equals(State.COMPLETED) || getState().equals(State.FAILED))){
			return true;
		}
		return false;
	}

	private void setResult(T result) {
		this.result = result;
	}
	
	private T getResult() {
		return this.result;
	}
	
	/**
	 * The get method return the result (either result or failure) only if the 
	 * task is completed. Otherwise it will wait for the task to be finished.
	 * 
	 * @return
	 * @throws ExecutionException
	 */
	public T get() throws ExecutionException {
		if(isDone()){
			if (getException() != null) {
				throw new ExecutionException(getException());
			}
			return getResult();
		}else {
			//wait for the computation to be completed
			while(!isDone()){
			}
			return getResult();
		}
	}
	
	/**
	 * The get method return the result (either result or failure) only if the 
	 * task is completed. Otherwise it will wait till timeout for the task to be finished.
	 * 
	 * @return
	 * @throws ExecutionException
	 * @throws TimeoutException 
	 */
	public T get(long nanos) throws ExecutionException, TimeoutException {
		if(isDone()){
			if (getException() != null) {
				throw new ExecutionException(getException());
			}
			return getResult();
		}else {
			//wait for the computation to be completed
			long t= System.currentTimeMillis();
			long end = t+nanos;
			while(System.currentTimeMillis() < end) {
			}
			if(getResult()!=null){
				return getResult();
			}else {
				throw new TimeoutException();
			}
		}
	}

	private void setRunning(boolean isRunning) {
		if(!isRunning) {
			if (getException() != null) {
				setState(State.FAILED);
			} else {
				setState(State.COMPLETED);
			}
		}
		this.isRunning = isRunning;
	}

	private void setState(State state) {
		this.state = state;
	}
	
	private State getState(){
		return this.state;
	}

	private Throwable getException() {
		return exception;
	}

	/**
	 * Causes this future to report an ExecutionException with the given throwable as 
	 * its cause, unless this Future has already been set or has been cancelled. 
	 * @param exception
	 */
	private void setException(Throwable exception) {
		this.exception = exception;
	}

	private Task<T> getTask() {
		return task;
	}
	
	private void setTask(Task<T> task) {
		this.task = task;
	}

}

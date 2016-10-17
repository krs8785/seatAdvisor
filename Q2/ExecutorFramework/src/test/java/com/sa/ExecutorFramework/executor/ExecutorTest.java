package com.sa.ExecutorFramework.executor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.sa.ExecutorFramework.Client;
import com.sa.ExecutorFramework.Client2;
import com.sa.ExecutorFramework.future.TaskFuture;


public class ExecutorTest {
	
	@Test
	public void testClient() throws InterruptedException, ExecutionException{
		Executor ex = new Executor(5);
		TaskFuture<Integer> fe = ex.submit(new Client(5));
		assertEquals(120,(int)fe.get());
		assertTrue(fe.isDone());
		ex.shutDown();
	}
	
	@Test
	public void testClient2()  {
		Executor ex = new Executor(2);
		try {
			TaskFuture<String> fe = ex.submit(new Client2("Karan"));
			System.out.println(fe.get(1000));
		} catch (Exception e) {
			assertTrue( e.getClass() == TimeoutException.class);
		} finally { 
			ex.shutDown();
		}
	}
	
	@Test
	public  void testMultipleClient() throws InterruptedException, ExecutionException {
		Executor ex = new Executor(3);
		List<TaskFuture<?>> futures = new ArrayList<TaskFuture<?>>();
		int count = 15;
		while(count > 0) {
			TaskFuture<Integer> fe = ex.submit(new Client(count--));
			futures.add(fe);			
		}
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(2004310016);
		set.add(1278945280);
		set.add(1932053504);
		set.add(479001600);
		set.add(39916800);
		set.add(3628800);
		set.add(362880);
		set.add(40320);
		set.add(5040);
		set.add(720);
		set.add(120);
		set.add(24);
		set.add(6);
		set.add(2);
		set.add(1);

		for(TaskFuture<?> f : futures) {
			assertTrue(set.contains(f.get()));
		}
		ex.shutDown();
	}
}

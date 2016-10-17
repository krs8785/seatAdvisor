package com.sa.ExecutorFramework;

import com.sa.ExecutorFramework.future.Task;

public class Client2 implements Task<String> {

	private String name;
	
	
	public Client2(String name) {
		setName(name);
	}
	
	public String call() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello "+getName();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}

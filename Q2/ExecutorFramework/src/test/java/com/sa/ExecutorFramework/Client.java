package com.sa.ExecutorFramework;

import com.sa.ExecutorFramework.future.Task;

public class Client implements Task<Integer> {

	private int clientId;
	public Client(int id) {
		setClientId(id);
	}
	
	public  Integer call() throws InterruptedException  {
		return factorial(getClientId());
	}
	
	private int factorial(int n) throws InterruptedException { 
		if(n < 0){
			return 0;
		}
		int result = 1; 
		while (n != 0) { 
			result = n * result; 
			n = n - 1; 
		} 
		return result;
	}

	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}

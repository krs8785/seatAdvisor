package com.sa.seatAdvisor.venue;

/**
 * Venue is the location where each performance is played.
 * It has a maximum capacity that it can hold.
 * 
 * @author karan
 *
 */
public class Venue {

	private final int capacity;
	
	public Venue(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}
}

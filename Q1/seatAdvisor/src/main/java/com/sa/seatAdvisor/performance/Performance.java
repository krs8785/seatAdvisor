package com.sa.seatAdvisor.performance;

import com.sa.seatAdvisor.venue.Venue;

/**
 * Performance class is the performance within an event. It runs for
 * a predefined time and start at a particular time. Each performance 
 * has a venue where it is performed.
 * 
 * @author karan
 *
 */
public class Performance {
	
	private String startTime;
	private final String totalPlayTime;
	private Venue venue;

	public Performance(String totalTime) {
		this.totalPlayTime = totalTime;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
}

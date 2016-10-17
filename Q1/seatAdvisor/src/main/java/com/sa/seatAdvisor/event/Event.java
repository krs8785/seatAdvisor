package com.sa.seatAdvisor.event;

import java.util.ArrayList;
import java.util.List;

import com.sa.seatAdvisor.performance.Performance;

/**
 * Event class represents the event happening in an organization. Every 
 * event has atleast one performance.
 * 
 * @author karan
 *
 */
public class Event {

	private String title;
	private String url;
	private List<Performance> performances;
	
	public Event(Performance p) {
		this.setPerformances(new ArrayList<Performance>());
		addPerformance(p);
	}
	
	public void addPerformance(Performance p) {
		getPerformances().add(p);
	}

	public Event(String title, String url) {
		setTitle(title);
		setUrl(url);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Performance> getPerformances() {
		return performances;
	}

	private void setPerformances(List<Performance> performances) {
		this.performances = performances;
	}
	
}

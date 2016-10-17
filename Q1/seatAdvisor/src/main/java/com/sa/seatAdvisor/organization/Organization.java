package com.sa.seatAdvisor.organization;

import java.util.ArrayList;
import java.util.List;

import com.sa.seatAdvisor.event.Event;

/**
 * Organization are customer who host events and sell tickets
 * @author karan
 *
 */
public class Organization {

	private List<Event> events;
	
	public Organization() {
		events = new ArrayList<Event>();
	}

	public List<Event> getEvents() {
		return events;
	}

	public void addEventToEventList(Event event) {
		getEvents().add(event);
	}
	
	public int getTotalNumberOfEvents() {
		if(getEvents() != null){
			return getEvents().size();
		}
		return 0;
	}
} 

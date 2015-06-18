package com.dp.observer;

/**
 * In brief, Observer Pattern = publisher + subscriber.
 * 
 * Observer pattern has been used in GUI action listener. Swing GUI example
 * shows how action listener works like an observer.
 * 
 * The following is a typical example about head hunter. There are two roles in
 * this diagram - HeadHunter and JobSeeker. Job seekers subscribe to a head
 * hunter, and head hunter notifies job seekers when there is a new job
 * opportunity.
 * 
 * @author arisupply
 *
 */
public class Main {

	public static void main(String[] args) {
		HeadHunter hh = new HeadHunter();
		hh.registerObserver(new JobSeeker("Mike"));
		hh.registerObserver(new JobSeeker("Chris"));
		hh.registerObserver(new JobSeeker("Jeff"));

		// Each time, a new job is added, all registered job seekers will get
		// noticed.
		hh.addJob("Google Job");
		hh.addJob("Yahoo Job");
		hh.addJob("Microsoft Job");
	}
}
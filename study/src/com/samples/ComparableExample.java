package com.samples;

class TV implements Comparable<TV> {
	private int size;
	private String brand;

	public TV(int size, String brand) {
		this.size = size;
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public int compareTo(TV tv) {

		if (this.getSize() > tv.getSize())
			return 1;
		else if (this.getSize() < tv.getSize())
			return -1;
		else
			return 0;
	}
}

public class ComparableExample {
	public static void main(String[] args) {
		TV tv1 = new TV(55, "Samsung");
		TV tv2 = new TV(60, "Sony");

		if (tv1.compareTo(tv2) > 0) {
			System.out.println(tv1.getBrand() + " is better.");
		} else {
			System.out.println(tv2.getBrand() + " is better.");
		}
	}
}
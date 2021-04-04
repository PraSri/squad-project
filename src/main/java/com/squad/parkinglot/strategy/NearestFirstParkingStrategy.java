package com.squad.parkinglot.strategy;

import java.util.TreeSet;

/**
 * Implementation of parking strategy which gives nearest empty slot
 * 
 * @author Prakhar
 *
 */

public class NearestFirstParkingStrategy implements ParkingStrategy {

	private TreeSet<Integer> slots;

	public NearestFirstParkingStrategy() {
		slots = new TreeSet<Integer>();
	}

	@Override
	public void add(int i) {
		slots.add(i);
	}

	@Override
	public int getSlot() {
		return slots.first();
	}

	@Override
	public void removeSlot(int availableSlot) {
		slots.remove(availableSlot);
	}

}

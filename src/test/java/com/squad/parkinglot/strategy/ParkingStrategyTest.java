package com.squad.parkinglot.strategy;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ParkingStrategyTest {

	ParkingStrategy parkingStrategy = null;

	@Before
	public void setup() {
		parkingStrategy = new NearestFirstParkingStrategy();
		for (int i = 1; i <= 10; i++) {
			parkingStrategy.add(i);
		}
	}

	@Test
	public void testNearestFirstParkingStrategy() {
		assertTrue(parkingStrategy.getSlot() == 1);
		assertTrue(parkingStrategy.getSlot() == 1);
		parkingStrategy.removeSlot(1);
		assertTrue(parkingStrategy.getSlot() == 2);
		parkingStrategy.removeSlot(2);
		assertTrue(parkingStrategy.getSlot() == 3);
	}

}

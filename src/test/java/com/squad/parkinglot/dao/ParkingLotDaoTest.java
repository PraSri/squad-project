package com.squad.parkinglot.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.squad.parkinglot.dao.impl.MemoryParkingManager;
import com.squad.parkinglot.model.Car;
import com.squad.parkinglot.model.Vehicle;
import com.squad.parkinglot.strategy.NearestFirstParkingStrategy;

public class ParkingLotDaoTest {

	private ParkingLotDao<Vehicle> parkingLotDao = null;

	@Test(expected = NullPointerException.class)
	public void testLeaveZeroCapacityParkingLot() {
		parkingLotDao = new MemoryParkingManager<Vehicle>(0, new NearestFirstParkingStrategy());
		parkingLotDao.leave(1);
	}

	@Test
	public void testParkZeroCapacityParkingLot() {
		parkingLotDao = new MemoryParkingManager<Vehicle>(0, new NearestFirstParkingStrategy());
		assertTrue(parkingLotDao.parkCar(new Car("test", "test")) == -1);
	}

	@Test
	public void testStatusZeroCapacityParkingLot() {
		parkingLotDao = new MemoryParkingManager<Vehicle>(0, new NearestFirstParkingStrategy());
		assertTrue(parkingLotDao.getStatus().size() == 0);
	}

}

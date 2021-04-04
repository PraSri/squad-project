package com.squad.parkinglot.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.squad.parkinglot.dao.impl.MemoryParkingManager;
import com.squad.parkinglot.model.Car;
import com.squad.parkinglot.model.Vehicle;

public class ParkingLotDaoTest {

	private ParkingLotDao<Vehicle> parkingLotDao = null;

	@Test(expected = NullPointerException.class)
	public void testLeaveZeroCapacityParkingLot() {
		parkingLotDao = MemoryParkingManager.getInstance(0, null);
		parkingLotDao.leave(1);
	}

	@Test
	public void testParkZeroCapacityParkingLot() {
		parkingLotDao = MemoryParkingManager.getInstance(0, null);
		assertTrue(parkingLotDao.parkCar(new Car("test", "test")) == -1);
	}

	@Test
	public void testStatusZeroCapacityParkingLot() {
		parkingLotDao = MemoryParkingManager.getInstance(0, null);
		assertTrue(parkingLotDao.getStatus().size() == 0);
	}

}

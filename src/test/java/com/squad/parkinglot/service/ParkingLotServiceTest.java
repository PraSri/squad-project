package com.squad.parkinglot.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.model.Car;
import com.squad.parkinglot.service.impl.ParkingServiceImpl;

public class ParkingLotServiceTest {

	private ParkingService parkingService = null;

	@Before
	public void setup() throws ParkingException {
		parkingService = new ParkingServiceImpl();
	}

	@Test
	public void testStatusParkingLot() throws ParkingException {
		parkingService.createParkingLot(2);
		int park1 = parkingService.park(new Car("test1", "1")).get();
		int park2 = parkingService.park(new Car("test2", "2")).get();
		List<String> regList = parkingService.getAllRegistrationNo("1");
		List<Integer> slotList = parkingService.getAllSlotNo("1");
		assertTrue(park1 == 1);
		assertTrue(park2 == 2);
		assertTrue(parkingService.getSlotNo("test1") == 1);
		assertTrue(regList.get(0).equals("test1"));
		assertTrue(slotList.get(0) == 1);
		parkingService.leave(1);
		slotList = parkingService.getAllSlotNo("1");
		assertTrue(slotList.isEmpty());
	}

}

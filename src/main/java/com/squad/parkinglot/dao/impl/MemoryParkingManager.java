/**
 * 
 */
package com.squad.parkinglot.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.squad.parkinglot.dao.ParkingLotDao;
import com.squad.parkinglot.model.Vehicle;
import com.squad.parkinglot.strategy.NearestFirstParkingStrategy;
import com.squad.parkinglot.strategy.ParkingStrategy;

/**
 * @author Prakhar
 * 
 *  In memory parking lot data manager singleton class
 *
 */
public class MemoryParkingManager<T extends Vehicle> implements ParkingLotDao<T> {

	private Integer capacity = null;
	private Integer availability = null;
	private ParkingStrategy parkingStrategy = null;

	private Map<Integer, Optional<T>> slotVehicleMap = null;

	private static MemoryParkingManager instance = null;

	public static <T extends Vehicle> MemoryParkingManager<T> getInstance(int capacity,
			ParkingStrategy parkingStrategy) {

		if (instance == null) {
			synchronized (MemoryParkingManager.class) {
				if (instance == null) {
					instance = new MemoryParkingManager<T>(capacity, parkingStrategy);
				}
			}
		}
		return instance;
	}

	private MemoryParkingManager(int capacity, ParkingStrategy parkingStrategy) {
		this.capacity = capacity;
		this.availability = this.capacity;
		this.parkingStrategy = parkingStrategy;
		if (parkingStrategy == null)
			parkingStrategy = new NearestFirstParkingStrategy();
		slotVehicleMap = new HashMap<>();
		for (int i = 1; i <= capacity; i++) {
			slotVehicleMap.put(i, Optional.empty());
			parkingStrategy.add(i);
		}
	}

	@Override
	public int parkCar(T vehicle) {
		int availableSlot;
		if (availability == 0) {
			return -1; // space not available
		} else {
			availableSlot = parkingStrategy.getSlot();
			if (slotVehicleMap.containsValue(Optional.of(vehicle)))
				return -2; // already exist

			slotVehicleMap.put(availableSlot, Optional.of(vehicle));
			parkingStrategy.removeSlot(availableSlot);
			availability--;
		}
		System.out.println(availableSlot);
		return availableSlot;
	}

	@Override
	public boolean leave(int slotNumber) {
		if (!slotVehicleMap.get(slotNumber).isPresent()) // Slot already empty
			return false;
		availability++;
		parkingStrategy.add(slotNumber);
		slotVehicleMap.put(slotNumber, Optional.empty());
		return true;
	}

	@Override
	public Map<Integer, Optional<T>> getStatus() {
		return slotVehicleMap;
	}

}

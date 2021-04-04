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
 * In memory parking lot data manager class
 * 
 * @author Prakhar
 * 
 *
 */
public class MemoryParkingManager<T extends Vehicle> implements ParkingLotDao<T> {

	private Integer capacity = null;
	private Integer availability = null;
	private ParkingStrategy parkingStrategy = null;

	private Map<Integer, Optional<T>> slotVehicleMap = null;

	public MemoryParkingManager(int capacity, ParkingStrategy parkingStrategy) {
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

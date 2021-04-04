/**
 * 
 */
package com.squad.parkinglot.dao;

import java.util.Map;
import java.util.Optional;

import com.squad.parkinglot.model.Vehicle;

/**
 * @author Prakhar
 *
 */
public interface ParkingLotDao<T extends Vehicle> {

	public int parkCar(T vehicle);

	public boolean leave(int slotNumber);

	public Map<Integer, Optional<T>> getStatus();

}

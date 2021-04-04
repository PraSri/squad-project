/**
 * 
 */
package com.squad.parkinglot.dao;

import java.util.Map;
import java.util.Optional;

import com.squad.parkinglot.model.Vehicle;

/**
 * 
 * Dao interface with basic operations
 * 
 * @author Prakhar
 * 
 * 
 */
public interface ParkingLotDao<T extends Vehicle> {

	public int parkCar(T vehicle);

	public boolean leave(int slotNumber);

	public Map<Integer, Optional<T>> getStatus();

}

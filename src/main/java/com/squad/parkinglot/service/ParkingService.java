/**
 * 
 */
package com.squad.parkinglot.service;

import java.util.List;
import java.util.Optional;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.model.Vehicle;

/**
 * @author Prakhar
 *
 */
public interface ParkingService {

	public void createParkingLot(int capacity) throws ParkingException;

	public Optional<Integer> park(Vehicle vehicle) throws ParkingException;

	public void leave(int slotNumber) throws ParkingException;

	public List<String> getAllRegistrationNo(String driverAge) throws ParkingException;
	
	public Integer getSlotNo(String registrationNo) throws ParkingException;
	
	public List<Integer> getAllSlotNo(String driverAge) throws ParkingException;

}

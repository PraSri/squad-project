/**
 * 
 */
package com.squad.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.squad.parkinglot.dao.ParkingLotDao;
import com.squad.parkinglot.dao.impl.MemoryParkingManager;
import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.model.Vehicle;
import com.squad.parkinglot.service.ParkingService;
import com.squad.parkinglot.strategy.NearestFirstParkingStrategy;

/**
 * Service implementation of parking lot, having main business logic
 * 
 * @author Prakhar
 *
 */
public class ParkingServiceImpl implements ParkingService {

	private ParkingLotDao<Vehicle> parkingLotDao = null;

	@Override
	public void createParkingLot(int capacity) throws ParkingException {

		if (parkingLotDao != null) {
			throw new ParkingException("Parking Lot Already exist!");
		}

		parkingLotDao = new MemoryParkingManager<Vehicle>(capacity, new NearestFirstParkingStrategy());

		System.out.println("Created parking of " + capacity + " slots");

	}

	@Override
	public Optional<Integer> park(Vehicle vehicle) throws ParkingException {

		checkParkingLotExist();

		Optional<Integer> value = Optional.empty();

		value = Optional.of(parkingLotDao.parkCar(vehicle));

		if (value.isPresent()) {
			if (value.get() == -1) {

				System.out.println("Space not available, capacity is full!");

			} else if (value.get() == -2) {

				System.out.println("Vehicle already exists in parking lot!!");

			} else {
				System.out.println("Car with vehicle registration number " + vehicle.getRegistrationNo()
						+ " has been parked at slot number " + value.get());
			}
		}

		return value;

	}

	@Override
	public void leave(int slotNumber) throws ParkingException {

		checkParkingLotExist();
		Vehicle vehicle = getVehicle(slotNumber);
		boolean leaveStatus = parkingLotDao.leave(slotNumber);
		if (leaveStatus == false) {
			System.out.println("Slot already vacant");
		} else {
			System.out.println("Slot number " + slotNumber + " vacated, the car with vehicle registration number "
					+ vehicle.getRegistrationNo() + " left the space, the driver of the car was of age "
					+ vehicle.getDriverAge());
		}

	}

	@Override
	public List<String> getAllRegistrationNo(String driverAge) throws ParkingException {
		Map<Integer, Optional<Vehicle>> statusMap = parkingLotDao.getStatus();
		List<Optional<Vehicle>> optionalVehicles = new ArrayList<Optional<Vehicle>>(statusMap.values());
		List<String> registrationNoList = new ArrayList<String>();
		for (Optional<Vehicle> v : optionalVehicles) {
			if (v.isPresent()) {
				Vehicle vehicle = v.get();
				if (vehicle.getDriverAge().equals(driverAge)) {
					registrationNoList.add(vehicle.getRegistrationNo());
				}
			}
		}
		return registrationNoList;
	}

	@Override
	public Integer getSlotNo(String registrationNo) throws ParkingException {
		Map<Integer, Optional<Vehicle>> statusMap = parkingLotDao.getStatus();
		for (Map.Entry<Integer, Optional<Vehicle>> me : statusMap.entrySet()) {
			int slotNo = me.getKey();
			Optional<Vehicle> optionalVehicle = me.getValue();
			if (optionalVehicle.isPresent()) {
				Vehicle vehicle = optionalVehicle.get();
				if (vehicle.getRegistrationNo().equals(registrationNo)) {
					return slotNo;
				}
			}
		}
		throw new ParkingException("No slot for vehicle of registrationNo " + registrationNo + " is available!");
	}

	@Override
	public List<Integer> getAllSlotNo(String driverAge) throws ParkingException {
		Map<Integer, Optional<Vehicle>> statusMap = parkingLotDao.getStatus();
		List<Integer> slotNoList = new ArrayList<Integer>();
		for (Map.Entry<Integer, Optional<Vehicle>> me : statusMap.entrySet()) {
			int slotNo = me.getKey();
			Optional<Vehicle> optionalVehicle = me.getValue();
			if (optionalVehicle.isPresent()) {
				Vehicle vehicle = optionalVehicle.get();
				if (vehicle.getDriverAge().equals(driverAge)) {
					slotNoList.add(slotNo);
				}
			}
		}
		return slotNoList;
	}

	private Vehicle getVehicle(Integer slotNo) throws ParkingException {
		Map<Integer, Optional<Vehicle>> statusMap = parkingLotDao.getStatus();
		try {
			if (statusMap.get(slotNo).isPresent())
				return parkingLotDao.getStatus().get(slotNo).get();
		} catch (Exception e) {
			throw new ParkingException("No vehicle is present on slot number " + slotNo);
		}

		throw new ParkingException("No vehicle is present on slot number " + slotNo);
	}

	private void checkParkingLotExist() throws ParkingException {
		if (parkingLotDao == null) {
			throw new ParkingException("Parking Lot Does not exist!");
		}
	}

}

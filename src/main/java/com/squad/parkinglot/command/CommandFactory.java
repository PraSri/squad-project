package com.squad.parkinglot.command;

import java.util.HashMap;
import java.util.Map;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.service.ParkingService;

/**
 * Singleton Factory class for getting implementations for respective commands
 * from user/file
 */
public class CommandFactory implements ICommand {

	private Map<String, ICommand> commandMap;
	private static CommandFactory commandFactoryInstance;

	private CommandFactory() {
		commandMap = new HashMap<>();
		commandMap.put("Create_parking_lot", CreateParkingLot.getInstance());
		commandMap.put("Park", ParkCommand.getInstance());
		commandMap.put("Slot_numbers_for_driver_of_age", SlotNumForAgeCommand.getInstance());
		commandMap.put("Slot_number_for_car_with_number", SlotNumForCarCommand.getInstance());
		commandMap.put("Leave", LeaveCommand.getInstance());
		commandMap.put("Vehicle_registration_number_for_driver_of_age", CarNumForAgeCommand.getInstance());
	}

	public Map<String, ICommand> getCommandMap() {
		return commandMap;
	}

	/**
	 * This is to make this class singleton
	 */
	public static synchronized CommandFactory getInstance() {
		if (commandFactoryInstance == null) {
			commandFactoryInstance = new CommandFactory();
		}
		return commandFactoryInstance;
	}

	@Override
	public void executeCommand(String[] cmd, ParkingService parkingService) throws ParkingException {
		if (!commandMap.containsKey(cmd[0])) {
			throw new ParkingException("InCorrect/Illegal Command ->" + cmd[0]);
		}
		getCommandMap().get(cmd[0]).executeCommand(cmd, parkingService);
	}

}

package com.squad.parkinglot.command;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.service.ParkingService;

/**
 * Interface to create different types of command and execute it with string
 * arguments while injecting parking service
 * 
 */
public interface ICommand {

	public void executeCommand(String cmd[], ParkingService parkingService) throws ParkingException;

}

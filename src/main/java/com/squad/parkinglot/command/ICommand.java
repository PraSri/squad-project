package com.squad.parkinglot.command;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.service.ParkingService;

public interface ICommand {

	public void executeCommand(String cmd[], ParkingService parkingService) throws ParkingException;

}

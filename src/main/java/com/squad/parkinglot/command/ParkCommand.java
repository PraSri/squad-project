/**
 * 
 */
package com.squad.parkinglot.command;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.model.Car;
import com.squad.parkinglot.model.Vehicle;
import com.squad.parkinglot.service.ParkingService;

/**
 * @author Prakhar
 *
 */
public class ParkCommand implements ICommand {

	private static ParkCommand instance = null;

	private ParkCommand() {
	}

	public static ParkCommand getInstance() {
		if (instance == null) {
			synchronized (ParkCommand.class) {

				if (instance == null) {
					instance = new ParkCommand();
				}

			}
		}

		return instance;
	}

	@Override
	public void executeCommand(String[] cmd, ParkingService parkingService) throws ParkingException {

		// Park KA-01-HH-1234 driver_age 21

		if (cmd.length < 4) {
			throw new ParkingException("Bad command ...please check the entered command!");
		}
		Vehicle car = new Car(cmd[1], cmd[3]);
		parkingService.park(car);
	}

}

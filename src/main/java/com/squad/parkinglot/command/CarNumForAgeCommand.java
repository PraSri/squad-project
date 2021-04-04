/**
 * 
 */
package com.squad.parkinglot.command;

import java.util.List;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.service.ParkingService;

/**
 * @author Prakhar
 *
 */
public class CarNumForAgeCommand implements ICommand {

	private static CarNumForAgeCommand instance = null;

	private CarNumForAgeCommand() {
	}

	public static CarNumForAgeCommand getInstance() {
		if (instance == null) {
			synchronized (CarNumForAgeCommand.class) {

				if (instance == null) {
					instance = new CarNumForAgeCommand();
				}

			}
		}

		return instance;
	}

	@Override
	public void executeCommand(String[] cmd, ParkingService parkingService) throws ParkingException {

//		Vehicle_registration_number_for_driver_of_age 18

		if (cmd.length < 2 || cmd[1] == null) {
			throw new ParkingException("Bad command ...please check the entered command!");
		} else {
			List<String> response = parkingService.getAllRegistrationNo(cmd[1]);
			System.out.println(response.toString().replace("[", "").replace("]", "").replaceAll("\\s", ""));
		}

	}

}

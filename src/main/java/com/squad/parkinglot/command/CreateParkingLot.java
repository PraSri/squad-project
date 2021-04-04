/**
 * 
 */
package com.squad.parkinglot.command;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.service.ParkingService;

/**
 * @author Prakhar
 *
 */
public class CreateParkingLot implements ICommand {

	private static CreateParkingLot instance = null;

	private CreateParkingLot() {
	}

	public static CreateParkingLot getInstance() {
		if (instance == null) {
			synchronized (CreateParkingLot.class) {

				if (instance == null) {
					instance = new CreateParkingLot();
				}

			}
		}

		return instance;
	}

	@Override
	public void executeCommand(String[] cmd, ParkingService parkingService) throws ParkingException {
		if (cmd[0] != null & cmd[0].length() > 0) {
			int capacity = Integer.parseInt(cmd[1]);
			parkingService.createParkingLot(capacity);
		}else {
			throw new ParkingException("Bad command ...please check the entered command!");
		}

	}

}

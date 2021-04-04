/**
 * 
 */
package com.squad.parkinglot.command;

import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.service.ParkingService;

/**
 * @author PrakharGuest
 *
 */
public class LeaveCommand implements ICommand {

	private static LeaveCommand instance = null;

	private LeaveCommand() {
	}

	public static LeaveCommand getInstance() {
		if (instance == null) {
			synchronized (LeaveCommand.class) {

				if (instance == null) {
					instance = new LeaveCommand();
				}

			}
		}

		return instance;
	}

	@Override
	public void executeCommand(String[] cmd, ParkingService parkingService) throws ParkingException {

		if (cmd.length < 2 || cmd[1] == null) {
			throw new ParkingException("Bad command ...please check the entered command!");
		} else {
			try {
				int slotNo = Integer.parseInt(cmd[1]);
				parkingService.leave(slotNo);
			} catch (Exception e) {
				throw new ParkingException("Invalid slot number : cause is " + e.getMessage());
			}
		}

	}

}

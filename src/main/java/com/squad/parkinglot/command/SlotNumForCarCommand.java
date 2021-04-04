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
public class SlotNumForCarCommand implements ICommand {
	
	private static SlotNumForCarCommand instance = null;

	private SlotNumForCarCommand() {
	}

	public static SlotNumForCarCommand getInstance() {
		if (instance == null) {
			synchronized (SlotNumForCarCommand.class) {

				if (instance == null) {
					instance = new SlotNumForCarCommand();
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
			Integer response = parkingService.getSlotNo(cmd[1]);
			System.out.println(response);
		}

	}

}

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
public class SlotNumForAgeCommand implements ICommand {

	private static SlotNumForAgeCommand instance = null;

	private SlotNumForAgeCommand() {
	}

	public static SlotNumForAgeCommand getInstance() {
		if (instance == null) {
			synchronized (SlotNumForAgeCommand.class) {

				if (instance == null) {
					instance = new SlotNumForAgeCommand();
				}

			}
		}

		return instance;
	}

	@Override
	public void executeCommand(String[] cmd, ParkingService parkingService) throws ParkingException {

//		Slot_numbers_for_driver_of_age 21

		if (cmd.length < 2 || cmd[1] == null) {
			throw new ParkingException("Bad command ...please check the entered command!");
		} else {
			List<Integer> response = parkingService.getAllSlotNo(cmd[1]);
			System.out.println(response.toString().replace("[", "").replace("]", "").replaceAll("\\s", ""));
		}

	}

}

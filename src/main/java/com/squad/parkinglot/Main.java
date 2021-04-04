package com.squad.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.squad.parkinglot.exception.ParkingException;
import com.squad.parkinglot.service.ParkingService;
import com.squad.parkinglot.service.impl.ParkingServiceImpl;
import com.squad.parkinglot.command.CommandFactory;

public class Main {

	public static void main(String[] args) {
		System.out.println("********** SQUAD PARKING LOT *********");
		BufferedReader bufferReader = null;
		ParkingService parkingService = null;
		String input = null;
		try {
			switch (args.length) {
			case 0: // Interactive: command-line input/output
			{
				System.out.println("Please provide input file path. Interactive: command-line i/o is not supported.");
				break;
			}
			case 1:// File input/output
			{
				File inputFile = new File(args[0]);
				try {
					bufferReader = new BufferedReader(new FileReader(inputFile));
					parkingService = new ParkingServiceImpl();
					int lineNo = 1;
					while ((input = bufferReader.readLine()) != null) {
						input = input.trim();
						if (input.length() > 0) {
//							System.out.println("Input file path provided : " + input);
							String[] cmd = input.split(" ");
							try {
								CommandFactory.getInstance().executeCommand(cmd, parkingService);
							} catch (ParkingException p) {
								System.out.println("BadCommand Exception due to ==>" + p.getMessage());
							}
						} else
							System.out.println("Incorrect Command Found at line: " + lineNo + " ,Input: " + input);
						lineNo++;
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				break;
			}
			default:
				System.out.println("Invalid input. Usage Style: java -jar <jar_file_path> <input_file_path>");
			}
		} finally {
			try {
				if (bufferReader != null)
					bufferReader.close();
			} catch (IOException e) {
			}
		}
	}
}

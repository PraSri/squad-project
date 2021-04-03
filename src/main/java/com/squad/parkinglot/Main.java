package com.squad.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		BufferedReader bufferReader = null;
		String input = null;
		try {
			switch (args.length) {
			case 0: // Interactive: command-line input/output
			{
				System.out.println(
						"Please provide input file path. Interactive: command-line input/output is not supported.");
				break;
			}
			case 1:// File input/output
			{
				File inputFile = new File(args[0]);
				try {
					bufferReader = new BufferedReader(new FileReader(inputFile));
					int lineNo = 1;
					while ((input = bufferReader.readLine()) != null) {
						input = input.trim();
						if (input.length() > 0) {
							System.out.println("Input file path provided : " + input);
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

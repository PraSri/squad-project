package com.squad.parkinglot;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainTest {

	@Test
	public void testMainClass() throws FileNotFoundException {
		String dir = System.getProperty("user.dir");
		String inputPath = dir + "\\" + "input.txt";
		String outputPathExpected = dir + "\\" + "output.txt";
		String outputPathActual = dir + "\\" + "out.txt";
		String[] args = new String[] { inputPath };
		PrintStream fileOut = new PrintStream("./out.txt");
		System.setOut(fileOut);
		Main.main(args);
		File firstFile = new File(outputPathExpected);
		File secondFile = new File(outputPathActual);
		assertTrue(isEqual(firstFile.toPath(), secondFile.toPath()));
		System.setOut(null);
	}

	private static boolean isEqual(Path firstFile, Path secondFile) {
		try {
			long size = Files.size(firstFile);
			if (size != Files.size(secondFile)) {
				return false;
			}

			if (size < 2048) {
				return Arrays.equals(Files.readAllBytes(firstFile), Files.readAllBytes(secondFile));
			}

			// Compare character-by-character
			try (BufferedReader bf1 = Files.newBufferedReader(firstFile);
					BufferedReader bf2 = Files.newBufferedReader(secondFile)) {

				int ch;
				while ((ch = bf1.read()) != -1) {
					if (ch != bf2.read()) {
						return false;
					}
				}
			}

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}

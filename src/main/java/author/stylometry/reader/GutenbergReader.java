package main.java.author.stylometry.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GutenbergReader {

	@SuppressWarnings("resource")
	public String parseInput(String inputFile)
	{
		String content = null;
		try {
			content = new Scanner(new File(inputFile)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
}
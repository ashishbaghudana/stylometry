package main.java.author.stylometry.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.java.author.stylometry.structure.Book;

public class GutenbergReader {

	public static String parseInput()
	{
		String file = "";
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\GitRepo\\FindAnagrams\\sowpods.txt"))){
			String currentLine = br.readLine();
				String str = "";
					while((str=br.readLine())!=null)
					{
						file += str;
					}				
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}
	
	public static void main(String[] arg)
	{
		Book b= new Book(parseInput(),"Manav");
		b.preprocess();
		
	}

}

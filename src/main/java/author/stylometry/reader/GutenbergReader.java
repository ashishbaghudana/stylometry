package main.java.author.stylometry.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.java.author.stylometry.preprocess.Tokenizer;
import main.java.author.stylometry.structure.Book;
import main.java.author.stylometry.structure.Sentence;

public class GutenbergReader {

	public static String parseInput()
	{
		String file = "";
		try (BufferedReader br = new BufferedReader(new FileReader("/home/ashish/workspace/stylometry/sample.txt"))) {
			String str;
			while((str=br.readLine())!=null)
			{
				file += str + "\n";
			}				
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public static void main(String[] arg)
	{
		String book = parseInput();
		Book b= new Book(book, "Manav");
		Sentence s= new Sentence("hdkjklasjdldajk");
		Tokenizer tokenizer = new Tokenizer("en-sent.bin", "en-token.bin");
		b.preprocess(tokenizer);
		System.out.println(b.punctuationDensity());
		System.out.println(b.avgLettersPerSentence());
		System.out.println(b.topNwords());
	}
}

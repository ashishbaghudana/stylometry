package main.java.author.stylometry.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.java.author.stylometry.preprocess.Tokenizer;
import main.java.author.stylometry.structure.Book;

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
		Tokenizer tokenizer = new Tokenizer("en-sent.bin", "en-token.bin");
		b.preprocess(tokenizer);
		System.out.println("Punctuation Density: " + b.punctuationDensity());
		System.out.println("Average Words per Sentence: " + b.avgWordsPerSentence());
		System.out.println("Average Letters per Sentence: " + b.avgLettersPerSentence());
		System.out.println("Average Words per Paragraph: " + b.avgWordsPerPara());
		System.out.println("Average Letter per Paragraph: " + b.avgLettersPerPara());
		System.out.println("Average Sentences per Paragraph: " + b.avgSentencesPerPara());
		System.out.println("Top 10 words used: " + b.wordFreqTopN(10));
	}
}
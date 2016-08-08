package main.java.author.stylometry.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.java.author.stylometry.preprocess.Tokenizer;
import main.java.author.stylometry.structure.Book;

public class GutenbergReader {

	public static String parseInput(String inputFile)
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
	
	public static void main(String[] arg)
	{
		String book = parseInput("/home/ashish/workspace/stylometry/books/hamlet.txt");
		Book b= new Book(book, "Leo Tolstoy");
		Tokenizer tokenizer = new Tokenizer("en-sent.bin", "en-token.bin");
		b.preprocess(tokenizer);
		System.out.println("Punctuation Density: " + b.punctuationDensity());
		System.out.println("Average Words per Sentence: " + b.avgWordsPerSentence());
		System.out.println("Average Letters per Sentence: " + b.avgLettersPerSentence());
		System.out.println("Average Words per Paragraph: " + b.avgWordsPerPara());
		System.out.println("Average Letter per Paragraph: " + b.avgLettersPerPara());
		System.out.println("Average Sentences per Paragraph: " + b.avgSentencesPerPara());
		System.out.println("Top 10 words used: " + b.wordFreqTopN(50));
	}
}
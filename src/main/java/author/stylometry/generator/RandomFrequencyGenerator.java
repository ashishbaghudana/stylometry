package main.java.author.stylometry.generator;

import main.java.author.stylometry.preprocess.Tokenizer;
import main.java.author.stylometry.reader.GutenbergReader;
import main.java.author.stylometry.structure.Book;

public class RandomFrequencyGenerator implements TextGenerator {
	public String generate(Book book, int n) {
		int count = 0;
		String s = "";
		RandomSelector rs = new RandomSelector(book.wordFrequency());
		while (count < n) {
			String word = rs.getRandom();
			s += word + " ";
			if (word.equals(".") || word.equals("?") || word.equals("!")) {
				count++;
				s += "\n";
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		GutenbergReader reader = new GutenbergReader();
    	Tokenizer tokenizer = new Tokenizer("en-sent.bin", "en-token.bin");
        Book b = new Book(reader.parseInput("books/romeojuliet.txt"));
        b.preprocess(tokenizer);
        RandomFrequencyGenerator rfg = new RandomFrequencyGenerator();
        System.out.println(rfg.generate(b, 10));
	}
}

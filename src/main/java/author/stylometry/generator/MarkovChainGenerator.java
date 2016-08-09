package main.java.author.stylometry.generator;

import java.util.HashMap;

import main.java.author.stylometry.preprocess.Tokenizer;
import main.java.author.stylometry.reader.GutenbergReader;
import main.java.author.stylometry.structure.Book;

public class MarkovChainGenerator implements TextGenerator {
	
	public String generate(Book book, int n) {
		HashMap<String, HashMap<String, Integer>>
			nextWordFrequencies = book.bigram();
		HashMap<String, Integer> startingWords = generateStartingWords(nextWordFrequencies);
		startingWords.putAll(nextWordFrequencies.get("."));
		startingWords.putAll(nextWordFrequencies.get("!"));
		startingWords.putAll(nextWordFrequencies.get("?"));
		startingWords.remove("\"");
		startingWords.remove("\'");
		startingWords.remove(")");
		RandomSelector rs = new RandomSelector(startingWords);
		String s = "";
		String nextWord = rs.getRandom();
		int count = 0;
		while (count < n) {
			s += nextWord + " ";
			if (nextWord.equals(".") || nextWord.equals("?")
					|| nextWord.equals("!")) {
				count++;
				s += "\n";
				rs = new RandomSelector(startingWords);
				nextWord = rs.getRandom();
			} else {
				rs = new RandomSelector(nextWordFrequencies.get(
						nextWord));
				nextWord = rs.getRandom();
			}
		}
		return s;
	}
	
	public HashMap<String, Integer> generateStartingWords(HashMap<String, HashMap<String, Integer>> nextWordFrequencies) {
		HashMap<String, Integer> startingWords = new HashMap<String, Integer>();
		startingWords.putAll(nextWordFrequencies.get("."));
		startingWords.putAll(nextWordFrequencies.get("!"));
		startingWords.putAll(nextWordFrequencies.get("?"));
		startingWords.remove("\"");
		startingWords.remove("\'");
		startingWords.remove(")");
		return startingWords;
	}
	
	public static void main(String[] args) {
		GutenbergReader reader = new GutenbergReader();
    	Tokenizer tokenizer = new Tokenizer("en-sent.bin", "en-token.bin");
        Book b = new Book(reader.parseInput("books/ofhumanbondage.txt"));
        b.preprocess(tokenizer);
        MarkovChainGenerator mcg = new MarkovChainGenerator();
        System.out.println(mcg.generate(b, 50));
	}
}
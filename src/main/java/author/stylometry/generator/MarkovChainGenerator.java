package main.java.author.stylometry.generator;

import java.util.TreeMap;

import main.java.author.stylometry.structure.Book;

public class MarkovChainGenerator implements TextGenerator {
	
	public String generate(Book book, int n) {
		TreeMap<String, Integer> vocabulary = book.sortedWordFrequency();
		return null;
	}
}

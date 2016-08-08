package main.java.author.stylometry.generator;

import main.java.author.stylometry.structure.Book;

public interface TextGenerator {
	public String generate(Book book, int n);
}

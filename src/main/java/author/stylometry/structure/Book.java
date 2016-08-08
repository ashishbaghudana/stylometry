package main.java.author.stylometry.structure;

import java.util.ArrayList;

public class Book {
	private String book;
	private ArrayList<Paragraph> paragraphs;
	
	public Book(String book) {
		this.book = book;
	}
	
	public void add(Paragraph paragraph) {
		this.paragraphs.add(paragraph);
	}
	
	public String toString() {
		return book;
	}
	
	public int punctuations() {
		int count = 0;
		for (Paragraph p : paragraphs) {
			count += p.punctuations();
		}
		return count;
	}
	
	public int length() {
		return paragraphs.size();
	}
	
	public int numSentences() {
		int count = 0;
		for (Paragraph p : paragraphs) {
			count += p.length();
		}
		return count;
	}
	
	public float punctuationDensity() {
		return (float) this.punctuations() / this.numSentences();
	}
}

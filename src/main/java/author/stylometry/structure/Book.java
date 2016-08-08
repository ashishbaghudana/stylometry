package main.java.author.stylometry.structure;

import java.util.ArrayList;

public class Book {
	private String book;
	private String author;
	private ArrayList<Paragraph> paragraphs;
	private Tokenizer tokenizer;
	
	public Book(String book, String author) {
		this.book = book;
		this.author = author;
		this.paragraphs = new ArrayList<Paragraph>();
		this.tokenizer = new Tokenizer();
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
	
	public String getAuthor() {
		return author;
	}
	
	public void preprocess() {
		for (String paragraph : tokenizer.paragraphs(book)) {
			Paragraph p = new Paragraph(paragraph);
			p.preprocess();
			this.add(p);
		}
	}
}

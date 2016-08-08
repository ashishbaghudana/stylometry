package main.java.author.stylometry.structure;

import java.util.ArrayList;
import main.java.author.stylometry.preprocess.Tokenizer;

public class Book {
	private String book;
	private String author;
	private ArrayList<Paragraph> paragraphs;
	
	public Book(String book, String author) {
		this.book = book;
		this.author = author;
		this.paragraphs = new ArrayList<Paragraph>();
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
	
	public void preprocess(Tokenizer tokenizer) {
		for (String paragraph : tokenizer.paragraphs(book)) {
			Paragraph p = new Paragraph(paragraph);
			p.preprocess(tokenizer);
			this.add(p);
		}
	}
}

package main.java.author.stylometry.structure;

import java.util.ArrayList;

import main.java.author.stylometry.metrics.SimpleMetrics;
import main.java.author.stylometry.preprocess.Tokenizer;

public class Collection {
	private ArrayList<Book> books;
	public SimpleMetrics metrics;
	public String author;
	
	public Collection(String author) {
		this.author = author;
		this.books = new ArrayList<Book>();
		this.metrics = new SimpleMetrics();
	}
	
	public void add(Book book) {
		this.books.add(book);
	}
	
	public void preprocess(Tokenizer tokenizer) {
		for (Book book : books) {
			book.preprocess(tokenizer);
		}
	}
	
	public void calculateMetrics() {
		for (Book book : books) {
			metrics.averageLettersPerParagraph += book.avgLettersPerPara();
			metrics.averageLettersPerSentence += book.avgLettersPerSentence();
			metrics.averageSentencesPerParagraph += book.avgSentencesPerPara();
			metrics.averageWordsPerParagraph += book.avgWordsPerPara();
			metrics.averageWordsPerSentence += book.avgWordsPerSentence();
		}
		metrics.averageLettersPerParagraph /= books.size();
		metrics.averageLettersPerSentence /= books.size();
		metrics.averageSentencesPerParagraph /= books.size();
		metrics.averageWordsPerParagraph /= books.size();
		metrics.averageWordsPerSentence /= books.size();
	}
	
	public float similarity(Collection c) {
		return Math.abs(c.metrics.averageLettersPerSentence - this.metrics.averageLettersPerSentence);
	}
}

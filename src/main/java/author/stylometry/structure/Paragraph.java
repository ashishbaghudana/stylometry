package main.java.author.stylometry.structure;

import java.util.ArrayList;

public class Paragraph {
	String paragraph;
	ArrayList<Sentence> sentences;
	Tokenizer tokenizer = new Tokenizer();
	
	public Paragraph(String paragraph) {
		this.paragraph = paragraph;
		this.sentences = new ArrayList<Sentence>();
		this.tokenizer = new Tokenizer();
	}
	
	public void add(Sentence sentence) {
		sentences.add(sentence);
	}
	
	public int punctuations() {
		int count = 0;
		for (Sentence sentence : sentences) {
			count+=sentence.punctuations();		
		}
		return count;
	}
	
	public int length() {
		return sentences.size();
	}
	
	public void preprocess() {
		for (String sent : tokenizer.sentences(paragraph)) {
			Sentence s = new Sentence(sent);
			s.preprocess();
			this.add(s);
		}
	}
	
	public String toString() {
		String p = "";
		for (Sentence s : sentences) {
			p += s.toString();
		}
		return p;
	}
}
package main.java.author.stylometry.structure;

import java.util.ArrayList;
import main.java.author.stylometry.preprocess.Tokenizer;

public class Paragraph {
	String paragraph;
	ArrayList<Sentence> sentences;
	
	public Paragraph(String paragraph) {
		this.paragraph = paragraph;
		this.sentences = new ArrayList<Sentence>();
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
	
	public void preprocess(Tokenizer tokenizer) {
		for (String sent : tokenizer.sentences(paragraph)) {
			Sentence s = new Sentence(sent);
			s.preprocess(tokenizer);
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
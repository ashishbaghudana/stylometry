package main.java.author.stylometry.structure;

import java.util.ArrayList;

public class Paragraph {
	String paragraph;
	ArrayList<Sentence> sentences;
	
	public Paragraph(String paragraph) {
		this.paragraph = paragraph;
	}
	
	public void add(String sentence) {
		Sentence w = new Sentence(sentence);
		sentences.add(w);
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
	
	public String toString() {
		String p = "";
		for (Sentence s : sentences) {
			p += s.toString();
		}
		return p;
	}
}
package main.java.author.stylometry.structure;

import java.util.ArrayList;

public class Sentence {
	String sentence;
	ArrayList<Word> words;
	
	public Sentence(String sentence) {
		this.sentence = sentence;
	}
	
	public void add(String word) {
		Word w = new Word(word);
		words.add(w);
	}
	
	public int punctuations() {
		int count = 0;
		for (Word word : words) {
			if (word.isPunctuation()) {
				count++;
			}
		}
		return count;
	}
	
	public int length() {
		return words.size();
	}
	
	public String toString() {
		String s = "";
		for (Word w : words) {
			s += w.toString();
		}
		return s;
	}
}

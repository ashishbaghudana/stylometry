package main.java.author.stylometry.structure;

import java.util.ArrayList;

import main.java.author.stylometry.preprocess.Tokenizer;

public class Sentence {
	String sentence;
	ArrayList<Word> words;
	Tokenizer tokenizer;

	public Sentence(String sentence) {
		this.sentence = sentence;
		this.words = new ArrayList<Word>();
	}

	public void add(Word word) {
		words.add(word);
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

	public ArrayList<Word> getWords() {
		return words;
	}
	
	public void preprocess(Tokenizer tokenizer) {
		for (String word : tokenizer.words(sentence)) {
			Word w = new Word(word);
			this.add(w);
		}
	}

	public String toString() {
		String s = "";
		for (Word w : words) {
			s += w.toString() + " ";
		}
		return s;
	}

	public int countWords() {
		return words.size();
	}

	public int countLetters() {
		int lettersPerSentence = 0;
		for (Word w : words) {
			lettersPerSentence += w.length();
		}
		return lettersPerSentence;
	}

	public ArrayList<String> topNwords() {
		ArrayList<String> topN = new ArrayList<String>();
		for (Word w : words) {
			if(!w.isPunctuation())
				topN.add(w.toString().toLowerCase());
		}
		return topN;
	}
}

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
			count += sentence.punctuations();
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

	public int countSentences() {

		return sentences.size();
	}

	public int countWords()
	{
		int wordsPerPara = 0;
		for(Sentence s:sentences)
		{
			wordsPerPara+=s.countWords();
		}
		return wordsPerPara;
	}
	
	public int countLetters()
	{
		int lettersPerParagraphs = 0;
		for (Sentence s: sentences)
		{
			lettersPerParagraphs += s.length();
		}
		return lettersPerParagraphs;
	}
	
	public ArrayList<String> topNwords() {
		ArrayList<String> topN = new ArrayList<String>();
		for (Sentence s : sentences) {
			topN.add(s.topNwords().toString());
		}
		return topN;
	}
}
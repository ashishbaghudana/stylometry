package main.java.author.stylometry.preprocess;

public class Tokenizer {
	
	public Tokenizer() {
		
	}
	
	public String[] paragraphs(String text) {
		return text.split("\n\n");
	}
	
	public String[] sentences(String text) {
		return null;
	}
	
	public String[] words(String text) {
		return null;
	}
}
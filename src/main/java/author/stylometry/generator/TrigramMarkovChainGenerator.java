package main.java.author.stylometry.generator;

import java.util.HashMap;

import main.java.author.stylometry.preprocess.Tokenizer;
import main.java.author.stylometry.reader.GutenbergReader;
import main.java.author.stylometry.structure.Book;

public class TrigramMarkovChainGenerator implements TextGenerator {

	public String generate(Book book, int n) {
		HashMap<String, HashMap<String, Integer>>
			nextWordFrequencies = book.bigram();
		HashMap<String, HashMap<String, Integer>>
			wordAfterFrequencies = book.trigram();
		HashMap<String, Integer> startingWords =
				generateStartingWords(nextWordFrequencies);
		RandomSelector rs = new RandomSelector(startingWords);
		String s = "";
		String start = rs.getRandom();
		s += start + " ";
		rs = new RandomSelector(nextWordFrequencies.get(start));
		String nextWord = rs.getRandom();
		int count = 0;
		while (count < n) {
			s += nextWord + " ";
			if (nextWord.equals(".") || nextWord.equals("?")
					|| nextWord.equals("!")) {
				count++;
				s += "\n";
				rs = new RandomSelector(startingWords);
				start = rs.getRandom();
				s += start + " ";
				rs = new RandomSelector(nextWordFrequencies.get(start));
				nextWord = rs.getRandom();
			} else {
				HashMap<String, Integer>
					newVocabulary =
					new HashMap<String, Integer>();
				newVocabulary.putAll(nextWordFrequencies.get(nextWord));
				newVocabulary.putAll(wordAfterFrequencies.get(start));
				rs = new RandomSelector(newVocabulary);
				start = nextWord;
				nextWord = rs.getRandom();
			}
		}
		return s;
	}

	/**
	 * Generate starting words, i.e. all those words that occur after
	 * a full stop, question mark or exclamation mark.
	 * @param nextWordFrequencies
	 * @return HashMap of starting words with their frequencies
	 */
	public HashMap<String, Integer> generateStartingWords(
			HashMap<String, HashMap<String, Integer>>
			nextWordFrequencies) {
		HashMap<String, Integer> startingWords =
				new HashMap<String, Integer>();
		addStartingWords(".", nextWordFrequencies, startingWords);
		addStartingWords("!", nextWordFrequencies, startingWords);
		addStartingWords("?", nextWordFrequencies, startingWords);
		startingWords.remove("\"");
		startingWords.remove("\'");
		startingWords.remove(")");
		return startingWords;
	}

	public void addStartingWords(String delimiter, 
			HashMap<String, HashMap<String, Integer>> nextWordFrequencies,
			HashMap<String, Integer> startingWords) {
		try {
			startingWords.putAll(nextWordFrequencies.get(delimiter));
		} catch (NullPointerException n) {
			
		}
	}

	public static void main(final String[] args) {
		GutenbergReader reader = new GutenbergReader();
    	Tokenizer tokenizer = new Tokenizer("en-sent.bin", "en-token.bin");
        Book b = new Book(reader.parseInput("books/ofhumanbondage.txt"));
        b.preprocess(tokenizer);
        TrigramMarkovChainGenerator mcg = new TrigramMarkovChainGenerator();
        System.out.println(mcg.generate(b, 20));
	}
}
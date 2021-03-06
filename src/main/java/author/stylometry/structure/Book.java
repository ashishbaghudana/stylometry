package main.java.author.stylometry.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.java.author.stylometry.preprocess.Tokenizer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Book {
	private String book;
	private ArrayList<Paragraph> paragraphs;


	public Book(String book, String author) {}


	public Book(String book) {

		this.book = book;
		this.paragraphs = new ArrayList<Paragraph>();
	}

	public void add(Paragraph paragraph) {
		this.paragraphs.add(paragraph);
	}

	public ArrayList<Paragraph> getParagraphs() {
		return this.paragraphs;
	}

	public String toString() {
		return book;
	}

	public int length() {
		return paragraphs.size();
	}

	public float punctuationDensity() {
		return (float) this.punctuations() / this.numSentences();
	}

//
//	public String getAuthor() {
//		return author;
//	}


	public void preprocess(Tokenizer tokenizer) {
		for (String paragraph : tokenizer.paragraphs(book)) {
			Paragraph p = new Paragraph(paragraph);
			p.preprocess(tokenizer);
			this.add(p);
		}
	}

	public float avgWordsPerSentence() {
		return (float) countWords() / numSentences();
	}

	public float avgLettersPerSentence() {
		return (float) countLetters() / numSentences();
	}

	public float avgWordsPerPara() {
		return (float) countWords() / numParagraphs();
	}

	public float avgLettersPerPara() {
		return (float) countLetters() / numParagraphs();
	}

	public float avgSentencesPerPara() {
		return (float) numSentences() / numParagraphs();
	}

	private int punctuations() {
		int count = 0;
		for (Paragraph p : paragraphs) {
			count += p.punctuations();
		}
		return count;
	}

	private int numSentences() {
		int count = 0;
		for (Paragraph p : paragraphs) {
			count += p.length();
		}
		return count;
	}

	private int numParagraphs() {
		return paragraphs.size();
	}

	private int countWords() {
		int wordsPerBook = 0;
		for (Paragraph p : paragraphs) {
			wordsPerBook += p.countWords();
		}
		return wordsPerBook;
	}

	private int countLetters() {
		int lettersPerBook = 0;
		for (Paragraph p : paragraphs) {
			lettersPerBook += p.countLetters();
		}
		return lettersPerBook;
	}

	public ArrayList<String> allWords() {
		ArrayList<String> topN = new ArrayList<String>();
		for (Paragraph p : paragraphs) {
			for (String a : p.topNwords()) {
				topN.add(a);
			}
		}
		return topN;
	}

	public HashMap<String, Integer> wordFrequency() {
		ArrayList<String> temp = allWords();
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();
		for (String s : temp) {
			if (frequency.containsKey(s)) {
				frequency.put(s, frequency.get(s) + 1);
			} else {
				frequency.put(s, 1);
			}
		}
		return frequency;
	}

	public TreeMap<String, Integer> sortedWordFrequency() {
		HashMap<String, Integer> map = wordFrequency();
		ValueComparator bvc = new ValueComparator(map);
		TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(bvc);
		sorted.putAll(map);
		return sorted;
	}

	public ArrayList<String> topNWords(int n) {
		Map<String, Integer> top = sortedWordFrequency();
		ArrayList<String> topN = new ArrayList<String>();

		int i=0;
		for (Entry<String, Integer> entry : top.entrySet())
	    {
	        if(i<n) {
	        	topN.add(entry.getKey());
	        	i++;
	        }
	    }

		return topN;
	}
	
//	public HashMap<String,ArrayList<String>> bigram()
//	{
//		HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
//		ArrayList<String> temp = allWords();		
//		for (int i=0;i<temp.size()-1;i++) {
//			String s = temp.get(i);
//			ArrayList<String> tempSet = new ArrayList<>();
//			//HashMap<String,Integer> tempSet2 = new HashMap<>();
//			if (map.containsKey(s)) {				
//				tempSet = map.get(s);
//			} 
//			tempSet.add(temp.get(i+1));
//	        map.put(s,tempSet);
//		}		
//
//		int i = 0;
//		for (Entry<String, Integer> entry : top.entrySet()) {
//			if (i < n) {
//				topN.add(entry.getKey());
//				i++;
//			}
//		}
//
//		return topN;
//	}

	public ArrayList<Character> allLetters() {
		ArrayList<Character> letters = new ArrayList<Character>();
		for (Paragraph p : paragraphs) {
			for (Character c : p.allLetters()) {
				letters.add(c);
			}

		}
		return letters;
	}

	public HashMap<Character, Integer> letterFrequency() {
		ArrayList<Character> temp = allLetters();
		HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
		for (Character c : temp) {
			if (frequency.containsKey(c)) {
				frequency.put(c, frequency.get(c) + 1);
			} else {
				frequency.put(c, 1);
			}
		}
		return frequency;
	}

	public TreeMap<Character, Integer> sortedLetterFrequency() {
		HashMap<Character, Integer> map = letterFrequency();
		ValueComparatorchar bvc = new ValueComparatorchar(map);
		TreeMap<Character, Integer> sorted = new TreeMap<Character, Integer>(bvc);
		sorted.putAll(map);
		return sorted;
	}

	public ArrayList<Character> topNLetters(int n) {
		Map<Character, Integer> top = sortedLetterFrequency();
		ArrayList<Character> topN = new ArrayList<Character>();
		int i = 0;
		for (Entry<Character, Integer> entry : top.entrySet()) {
			if (i < n) {
				topN.add(entry.getKey());
				i++;
			}
		}

		return topN;
	}

	public HashMap<String, HashMap<String, Integer>> bigram() {
		HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();
		ArrayList<String> words = allWords();
		for (int i = 0; i < words.size() - 1; i++) {
			String s = words.get(i);
			HashMap<String, Integer> tempSet = new HashMap<>();
			if (map.containsKey(s)) {
				tempSet = map.get(s);
			}
			if (tempSet.containsKey(words.get(i + 1)))
				tempSet.put(words.get(i+1), tempSet.get(words.get(i+1)) + 1);
			else
				tempSet.put(words.get(i + 1), 1);
			map.put(s, tempSet);
		}

		return map;
	}


public HashMap<String, HashMap<String, Integer>> trigram() {
	HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();
	ArrayList<String> words = allWords();
	for (int i = 0; i < words.size() - 2; i++) {
		String s = words.get(i);
		HashMap<String, Integer> tempSet = new HashMap<>();
		if (map.containsKey(s)) {
			tempSet = map.get(s);
		}
		if (tempSet.containsKey(words.get(i + 2)))
			tempSet.put(words.get(i+2), tempSet.get(words.get(i+2)) + 1);
		else
			tempSet.put(words.get(i + 2), 1);
		map.put(s, tempSet);
	}

	return map;
}


}

class ValueComparator implements Comparator<String> {
	HashMap<String, Integer> base;

	public ValueComparator(HashMap<String, Integer> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with
	// equals.
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}

class ValueComparatorchar implements Comparator<Character> {
    HashMap<Character, Integer> base;

    public ValueComparatorchar(HashMap<Character, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(Character a, Character b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}


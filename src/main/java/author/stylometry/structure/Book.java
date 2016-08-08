package main.java.author.stylometry.structure;

import java.util.ArrayList;
import main.java.author.stylometry.preprocess.Tokenizer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Book {
	private String book;
	private String author;
	private ArrayList<Paragraph> paragraphs;
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
			for(String a : p.topNwords())
			{
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
	
	public TreeMap<String, Integer> sortedWordFrequency()
	{
		HashMap<String, Integer> map = wordFrequency();
		ValueComparator bvc = new ValueComparator(map);
        TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(bvc);
        sorted.putAll(map);
        return sorted;
	}
	
	public ArrayList<String> topNWords(int n) 
	{
		Map<String,Integer> top = sortedWordFrequency();
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

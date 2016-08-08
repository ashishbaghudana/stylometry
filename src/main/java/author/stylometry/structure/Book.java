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
	public Book(String book, String author) {
		this.book = book;
		this.author = author;
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
	
	public String getAuthor() {
		return author;
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
			lettersPerBook += p.length();
		}
		return lettersPerBook;
	}

	public ArrayList<String> topNwords() {
		ArrayList<String> topN = new ArrayList<String>();
		for (Paragraph p : paragraphs) {
			topN.add(p.topNwords().toString());
		}
		return topN;
	}

	public HashMap<String, Integer> wordFreq() {
		ArrayList<String> temp = topNwords();
		HashMap<String, Integer> top = new HashMap<String, Integer>();
		for (String s : temp) {
			if (top.containsKey(s)) {
				top.put(s, top.get(s) + 1);
			} else {
				top.put(s, 1);
			}
		}
	return top;
	}
	
	public TreeMap<String, Integer> sortedWordFreq()
	{
		HashMap<String, Integer> map= wordFreq();
		ValueComparator bvc = new ValueComparator(map);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        return sorted_map;
	}
	
	public ArrayList<String> wordFreqTopN(int n) 
	{
		ArrayList<String> topN= new ArrayList<String>();
		Map<String,Integer> top= sortedWordFreq();
		int i=0;
		//Set<String> keys = sortedWordFreq().keySet();
		for (Entry<String, Integer> entry : top.entrySet())
	    {
	        if(i<10)
	        	{
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

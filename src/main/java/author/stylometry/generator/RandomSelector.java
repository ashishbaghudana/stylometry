package main.java.author.stylometry.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import main.java.author.stylometry.preprocess.Tokenizer;
import main.java.author.stylometry.reader.GutenbergReader;
import main.java.author.stylometry.structure.Book;
import main.java.author.stylometry.structure.Collection;

public class RandomSelector {
    
    Random rand;
    int totalSum;
    ArrayList<String> words;
    Map<String, Integer> map;

    public RandomSelector(Map<String, Integer> map) {
    	rand = new Random();
    	totalSum = 0;
    	this.map = map;
    	words = new ArrayList<String>(map.keySet());
        for (int value : map.values()) {
            totalSum = totalSum + value;
        }
    }

    public String getRandom() {
        int index = rand.nextInt(totalSum);
        int sum = 0;
        int i = 0;
        while(sum < index) {
             sum = sum + map.get(words.get(i));
             i++;
        }
        return words.get(Math.max(0,i-1));
    }
    
    public static void main(String[] args) {
    	GutenbergReader reader = new GutenbergReader();
    	Tokenizer tokenizer = new Tokenizer("en-sent.bin", "en-token.bin");
        Book b = new Book(reader.parseInput("books/romeojuliet.txt"));
        b.preprocess(tokenizer);
        HashMap<String, Integer> frequencies = b.wordFrequency();
        RandomSelector r = new RandomSelector(frequencies);
    }
}
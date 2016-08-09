package main.java.author.stylometry.structure;

import java.util.ArrayList;

public class Word {

	private String word;
	private char letters;
	
	public Word(String word)
	{
		this.word=word;
	}
	public int length()
	{
		return word.length();
		
	}
	
	
	public String toString()
	{
		return word.toString();
	}
	public boolean isPunctuation()
	{
		if(word.matches("!|,|.|\"|;|:|\'|\\?"))
			return true;
		else
			return false;
		
	}
	
	public ArrayList<Character> allLetters()
	{
		ArrayList<Character> letters = new ArrayList<Character>();
		char a[]=word.toCharArray();
		
		for(int x=0;x<a.length;x++)
		{
			letters.add(a[x]);
		}
		return letters;
		
	}
}



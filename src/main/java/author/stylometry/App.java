package main.java.author.stylometry;

import main.java.author.stylometry.preprocess.Tokenizer;
import main.java.author.stylometry.reader.GutenbergReader;
import main.java.author.stylometry.structure.Book;
import main.java.author.stylometry.structure.Collection;
import main.java.author.stylometry.structure.Sentence;
import main.java.author.stylometry.structure.Word;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main (String[] args) {
    	GutenbergReader reader = new GutenbergReader();
    	Tokenizer tokenizer = new Tokenizer("en-sent.bin", "en-token.bin");
        Collection shakespeare = new Collection("Shakespeare");
        shakespeare.add(new Book(reader.parseInput("books/romeojuliet.txt")));
        shakespeare.add(new Book(reader.parseInput("books/juliuscaesar.txt")));
        shakespeare.add(new Book(reader.parseInput("books/hamlet.txt")));
        shakespeare.add(new Book(reader.parseInput("books/macbeth.txt")));
     
        
        
        shakespeare.preprocess(tokenizer);
        
        shakespeare.calculateMetrics();
        
        Collection somerset = new Collection("Maugham");
        somerset.add(new Book(reader.parseInput("books/themagician.txt")));
        somerset.add(new Book(reader.parseInput("books/ofhumanbondage.txt")));
        somerset.add(new Book(reader.parseInput("books/moonandsixpence.txt")));
        
        somerset.preprocess(tokenizer);
        somerset.calculateMetrics();
        
        Collection unknown = new Collection("unknown");
        unknown.add(new Book(reader.parseInput("books/othello.txt")));
        
        unknown.preprocess(tokenizer);
        unknown.calculateMetrics();
        
        if (unknown.similarity(shakespeare) < unknown.similarity(somerset)) {
        	System.out.println(shakespeare.author);
        } else {
        	System.out.println(somerset.author);
        }
    }
}

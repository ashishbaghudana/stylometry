package main.java.author.stylometry.preprocess;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Tokenizer class that tokenizes:
 * 1. Text into paragraphs
 * 2. Paragraphs into sentences
 * 3. Sentences into words
 * @author ashish
 *
 */
public class Tokenizer {
	/**
	 * Sentence model that helps tokenize paragraphs into sentences
	 * by detecting sentence boundaries.
	 */
	private SentenceModel sentenceModel;
	/**
	 * Word tokenizer model that helps tokenize sentences into words.
	 */
	private TokenizerModel tokenModel;
	/**
	 * 
	 */
	private SentenceDetectorME sentenceDetector;
	private TokenizerME wordTokenizer;

	/**
	 * Constructor for Tokenizer class.
	 * Creates a sentence and token model and creates a Sentence Detector
	 * and Word Tokenizer
	 * @param sentenceFile Binary file for sentence model.
	 * @param tokenFile Binary file for token model.
	 */
	public Tokenizer(final String sentenceFile, final String tokenFile) {
		this.createSentenceModel(sentenceFile);
		this.createTokenModel(tokenFile);
		this.sentenceDetector = new SentenceDetectorME(
				this.sentenceModel);
		this.wordTokenizer = new TokenizerME(this.tokenModel);
	}

	/**
	 * Create a Token Model from binary file.
	 * @param file Binary file for token model.
	 */
	private void createTokenModel(final String file) {
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(file);
			this.tokenModel = new TokenizerModel(modelIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void createSentenceModel(String file) {
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(file);
			this.sentenceModel = new SentenceModel(modelIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
			    } catch (IOException e) {
			    	e.printStackTrace();
			    }
			}
		}
	}
	
	public String[] paragraphs(String text) {
		return text.split("\\n\\n");
	}
	
	public String[] sentences(String text) {
		return this.sentenceDetector.sentDetect(text);
	}
	
	public String[] words(String text) {
		return wordTokenizer.tokenize(text);
	}
}
package main.java.author.stylometry.preprocess;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Tokenizer {
	
	private SentenceModel sentenceModel;
	private TokenizerModel tokenModel;
	private SentenceDetectorME sentenceDetector;
	private TokenizerME wordTokenizer;
	
	public Tokenizer(String sentenceFile, String tokenFile) {
		this.createSentenceModel(sentenceFile);
		this.createTokenModel(tokenFile);
		this.sentenceDetector = new SentenceDetectorME(this.sentenceModel);
		this.wordTokenizer = new TokenizerME(this.tokenModel);
	}
	
	private void createTokenModel(String file) {
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
		return text.split("\n\n");
	}
	
	public String[] sentences(String text) {
		return this.sentenceDetector.sentDetect(text);
	}
	
	public String[] words(String text) {
		return wordTokenizer.tokenize(text);
	}
}
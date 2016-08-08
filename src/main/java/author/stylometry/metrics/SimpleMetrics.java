package main.java.author.stylometry.metrics;

public class SimpleMetrics {
	public float averageWordsPerSentence;
	public float averageLettersPerSentence;
	public float averageWordsPerParagraph;
	public float averageLettersPerParagraph;
	public float averageSentencesPerParagraph;
	
	public SimpleMetrics() {
		this.averageLettersPerParagraph = 0;
		this.averageLettersPerSentence = 0;
		this.averageSentencesPerParagraph = 0;
		this.averageWordsPerParagraph = 0;
		this.averageWordsPerSentence = 0;
	}
}

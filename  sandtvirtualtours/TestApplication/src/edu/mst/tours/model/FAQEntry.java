package edu.mst.tours.model;

public class FAQEntry {
	
	private String question;
	private String answer;
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public int hashCode() {
		return getQuestion().hashCode() + getAnswer().hashCode();
	}
	
	@Override
	public String toString() {
		return getQuestion() + " - " + getAnswer();
	}
}

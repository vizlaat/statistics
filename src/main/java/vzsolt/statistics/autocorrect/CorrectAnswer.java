package vzsolt.statistics.autocorrect;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CorrectAnswer {

	private String question;
	
	private int questionID;
	
	private List<SimpleAnswer> answers;
	
	
	public CorrectAnswer() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}



	public List<SimpleAnswer> getAnswers() {
		return answers;
	}



	public void setAnswers(List<SimpleAnswer> answers) {
		this.answers = answers;
	}



	public int getQuestionID() {
		return questionID;
	}



	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	
	

}

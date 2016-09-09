package vzsolt.statistics.autocorrect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vzsolt.statistics.analyze.Response;

@Component
public class AutoCorrector {

	public AutoCorrector() {
		// TODO Auto-generated constructor stub
	}

	public List<Response> correctSurvey(List<Response> surveyResponse, List<CorrectAnswer> correctAnswers) {
		List<Response> correctedResponses = new ArrayList<>();
		List<Integer> questionIDs = getQuestionIDS(correctAnswers);
		for (Response response : surveyResponse) {
			Response correctedResponse = response.copyResponse();
			Map<Integer,String> answerMap = correctedResponse.getAnswerMap();
			for(CorrectAnswer corrA: correctAnswers) {
				boolean correct = false;
				String userAnswer = answerMap.get(corrA.getQuestionID());
				for(SimpleAnswer simpA:corrA.getAnswers()) {
					if(userAnswer.equals(simpA.getAnswer())) {
						correctedResponse.addSimpleAnswerByID(corrA.getQuestionID(), simpA.getPoint());
						correct = true;
					}
				}
				if(!correct) {
					correctedResponse.addSimpleAnswerByID(corrA.getQuestionID(), "0");
				}
			}
			int points = countPoints(correctedResponse, questionIDs);
			correctedResponse.addSimpleAnswer("Points", String.valueOf(points));
			correctedResponses.add(correctedResponse);
		}
		return correctedResponses;
	}
	
	private int countPoints(Response response, List<Integer> questionIDs) {
		int point = 0;
		for(int questionID: questionIDs) {
			point += Integer.valueOf(response.getAnswerMap().get(questionID));
		}
		return point;
	}
	
	private List<Integer> getQuestionIDS(List<CorrectAnswer> correctAnswers){
		List<Integer> questionIDs = new LinkedList<>();
		for(CorrectAnswer correctAnswer: correctAnswers) {
			questionIDs.add(correctAnswer.getQuestionID());
		}
		return questionIDs;
	}

}

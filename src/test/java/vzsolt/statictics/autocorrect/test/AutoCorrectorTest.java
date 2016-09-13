package vzsolt.statictics.autocorrect.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vzsolt.statistics.analyze.Response;
import vzsolt.statistics.autocorrect.AutoCorrector;
import vzsolt.statistics.autocorrect.CorrectAnswer;
import vzsolt.statistics.autocorrect.SimpleAnswer;

public class AutoCorrectorTest {

	public AutoCorrectorTest() {
		// TODO Auto-generated constructor stub
	}

	private List<Response> initResponses = new LinkedList<>();
	private List<CorrectAnswer> corrAns1 = new LinkedList<>();
	private List<CorrectAnswer> corrAns2 = new LinkedList<>();
	
	
	
	
	
	@Before
	public void initResponseAndFilter() {
		Response response1 = new Response();
		response1.addSimpleAnswer("first", "not a number");
		response1.addSimpleAnswer("number", "1");
		response1.addSimpleAnswer("double", "2.1");
		for(int i=0; i<10; i++) {
			initResponses.add(response1.copyResponse());
		}
		
		corrAns1.add(createCorrectAnswer("first", 0, "not a number", "1"));
		corrAns2.add(createCorrectAnswer("first", 0, "something else", "1"));
		
		
	}
	
	private CorrectAnswer createCorrectAnswer(String question, int id, String answer, String point) {
		CorrectAnswer corrAnswer = new CorrectAnswer();
		corrAnswer.setQuestion(question);
		corrAnswer.setQuestionID(id);
		SimpleAnswer simpleAnswer1 = new SimpleAnswer();
		simpleAnswer1.setAnswer(answer);
		simpleAnswer1.setPoint(point);
		List<SimpleAnswer> simpleAList = new LinkedList<>();
		simpleAList.add(simpleAnswer1);
		corrAnswer.setAnswers(simpleAList);
		return corrAnswer;
	}
	
	
	@Test
	public void autoCorrectWithCorrectAnswer() {
		AutoCorrector autCorr = new AutoCorrector();
		List<Response> corrected = autCorr.correctSurvey(initResponses,corrAns1);
		assertEquals("1",corrected.get(0).getAnswerMap().get(0));		
	}
	
	@Test
	public void autoCorrectWithInCorrectAnswer() {
		AutoCorrector autCorr = new AutoCorrector();
		List<Response> corrected = autCorr.correctSurvey(initResponses,corrAns2);
		assertEquals("0",corrected.get(0).getAnswerMap().get(0));		
	}
	
	@Test
	public void autoCorrectWithCorrectAnswerPointCheck() {
		AutoCorrector autCorr = new AutoCorrector();
		List<Response> corrected = autCorr.correctSurvey(initResponses,corrAns1);
		assertEquals("1",corrected.get(0).getAnswerMap().get(3));		
	}
	
	@Test
	public void autoCorrectWithInCorrectAnswerPointCheck() {
		AutoCorrector autCorr = new AutoCorrector();
		List<Response> corrected = autCorr.correctSurvey(initResponses,corrAns2);
		assertEquals("0",corrected.get(0).getAnswerMap().get(3));		
	}
	
}

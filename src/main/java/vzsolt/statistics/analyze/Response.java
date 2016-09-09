package vzsolt.statistics.analyze;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;


public class Response {

	private Map<Integer,String> questionMap;
	private Map<Integer,String> answerMap; 
	
	
	public Response() {
		// TODO Auto-generated constructor stub
		questionMap = new TreeMap<>();
		answerMap = new TreeMap<>();
	}
	
	public Response copyResponse() {
		Response newResponse = new Response();
		newResponse.answerMap = new TreeMap<Integer, String>(this.answerMap);
		newResponse.questionMap =new TreeMap<Integer,String>(this.questionMap);
		return newResponse;
	}
	
	

	public void addSimpleAnswerByID(int ID, String answer) {
		if(questionMap.containsKey(ID)) {
			answerMap.put(ID, answer);
		}else {
			throw new IllegalArgumentException("There is no such question ID!");
		}
		
	}
	
	public boolean addSimpleAnswer(String question, String answer) {
		if(questionMap.containsValue(question)) {
			for(Entry<Integer, String> entry: questionMap.entrySet()) {
				if(Objects.equals(question, entry.getValue())) {
					answerMap.put(entry.getKey(), answer);
				}
			}
			return false;
		}else {
			questionMap.put(questionMap.size(), question);
			answerMap.put(questionMap.size()-1, answer);
			return true;
		}		
	}
	
	public boolean addQuestion(String question) {
		if(questionMap.containsValue(question)) {
			return false;
		}else {
			questionMap.put(questionMap.size(), question);			
			return true;
		}
	}
	
	public Map<Integer, String> getQuestionMap() {
		return Collections.unmodifiableMap(questionMap);
	}

	public Map<Integer, String> getAnswerMap() {
		return Collections.unmodifiableMap(answerMap);
	}
	
	
	
}

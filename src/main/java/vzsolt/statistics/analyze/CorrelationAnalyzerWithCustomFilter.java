package vzsolt.statistics.analyze;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.stereotype.Service;

import vzsolt.statistics.utility.Filter;

@Service
public class CorrelationAnalyzerWithCustomFilter {

	private int questionOneID;
	private int questionTwoID;
	private List<Filter> filters;
	
	
	
	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public void clearFilters() {
		filters.clear();
	}
	
	public void addFilter(Filter filter) {
		filters.add(filter);
	}
	
	public CorrelationAnalyzerWithCustomFilter() {
		// TODO Auto-generated constructor stub
	}

	public int getQuestionOneID() {
		return questionOneID;
	}

	public void setQuestionOneID(int questionOneID) {
		this.questionOneID = questionOneID;
	}

	public int getQuestionTwoID() {
		return questionTwoID;
	}

	public void setQuestionTwoID(int questionTwoID) {
		this.questionTwoID = questionTwoID;
	}

	public double analyze(List<Response> surveyResult) {
		List<Double> firstNumbers = new ArrayList<>();
		List<Double> secondNumbers = new ArrayList<>();
		for(Response response:surveyResult) {
			try {
				if(filterMatch(response)) {
					if(isNumber(response)) {
					firstNumbers.add(Double.valueOf(response.getAnswerMap().get(questionOneID)));
					secondNumbers.add(Double.valueOf(response.getAnswerMap().get(questionTwoID)));
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			
		}
		
		double[] secondArray = ArrayUtils.toPrimitive(secondNumbers.toArray(new Double[] {}));
		double[] firstArray = ArrayUtils.toPrimitive(firstNumbers.toArray(new Double[] {}));
				
		return new PearsonsCorrelation().correlation(firstArray,secondArray);
	}


	private boolean isNumber(Response response) {
		Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+");
		return (p.matcher(response.getAnswerMap().get(questionOneID)).matches()&&p.matcher(response.getAnswerMap().get(questionTwoID)).matches());
	}
	
	private boolean filterMatch(Response response) {
		for(Filter filter:filters) {
			if(!response.getAnswerMap().get(filter.getQuestionID()).equals(filter.getFilterValue())) {
				return false;
			}
		}
		
		return true;
	}

}

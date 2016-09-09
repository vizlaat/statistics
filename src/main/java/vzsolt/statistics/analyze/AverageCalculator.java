package vzsolt.statistics.analyze;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import vzsolt.statistics.utility.AverageResult;
import vzsolt.statistics.utility.Filter;

@Service
public class AverageCalculator {

	public AverageCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public List<AverageResult> calculateAverage(List<Response> responses, List<Filter> filters){
		List<Response> responsesCopy = new ArrayList<>(responses);
		List<AverageResult> averages = new LinkedList<>();
		Set<Integer> questionIDSet = responses.get(0).getAnswerMap().keySet();
		for(Integer questionID :questionIDSet) {
			double sum = 0;
			int count=0;
			for(Iterator<Response> iter = responsesCopy.iterator(); iter.hasNext();) {
				Response actualResponse = iter.next();
				if(filterMatch(actualResponse,filters)) {
					if(isNumber(actualResponse, questionID)) {
						sum += Double.valueOf(actualResponse.getAnswerMap().get(questionID));
						count += 1;
					}					
				}else {
					iter.remove();
				}
			}
			Double average = null;
			if(count != 0) {
				average = sum/count;
				AverageResult avRes = new AverageResult();
				avRes.setAverage(average);
				avRes.setQuestionID(questionID);
				avRes.setCount(count);
				averages.add(avRes);
			}
		}
		return averages;
	}
	
	private boolean isNumber(Response response, int questionID) {
		Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+");
		return (p.matcher(response.getAnswerMap().get(questionID)).matches());
	}
	
	private boolean filterMatch(Response response, List<Filter> filters) {
		for(Filter filter: filters) {
			if(!response.getAnswerMap().get(filter.getQuestionID()).equals(filter.getFilterValue())) {
				return false;
			}
		}
		return true;
	}

}

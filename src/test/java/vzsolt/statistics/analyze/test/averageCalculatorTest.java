package vzsolt.statistics.analyze.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import vzsolt.statistics.analyze.AverageCalculator;
import vzsolt.statistics.analyze.Response;
import vzsolt.statistics.utility.AverageResult;
import vzsolt.statistics.utility.Filter;

public class averageCalculatorTest {

	public averageCalculatorTest() {
		// TODO Auto-generated constructor stub
	}
	
	private List<Response> initResponses = new LinkedList<>();
	private List<Filter> filters1 = new LinkedList<>();
	private List<Filter> filters2 = new LinkedList<>();
	
	
	
	@Before
	public void initResponseAndFilter() {
		Response response1 = new Response();
		response1.addSimpleAnswer("first", "not a number");
		response1.addSimpleAnswer("number", "1");
		response1.addSimpleAnswer("double", "2.1");
		for(int i=0; i<10; i++) {
			initResponses.add(response1.copyResponse());
		}
		
		Filter filter1 = new Filter();
		filter1.setQuestionID(0);
		filter1.setFilterValue("not a number");
		filters1.add(filter1);
		filter1 = new Filter();
		filter1.setQuestionID(0);
		filter1.setFilterValue("something else");
		filters2.add(filter1);
	}
	
	@Test
	public void calculateAverageWithoutFiltersSize() {
		AverageCalculator avCal = new AverageCalculator();
		List<Filter> emptyFilters = new LinkedList<>();
		List<AverageResult> results = avCal.calculateAverage(initResponses, emptyFilters);
		assertEquals(2,results.size());
	}
	
	@Test
	public void calculateAverageWithoutFiltersIntResult() {
		AverageCalculator avCal = new AverageCalculator();
		List<Filter> emptyFilters = new LinkedList<>();
		List<AverageResult> results = avCal.calculateAverage(initResponses, emptyFilters);
		assertEquals(1, results.get(0).getAverage(), 0.01);
	}
	
	@Test
	public void calculateAverageWithoutFiltersDoubleResult() {
		AverageCalculator avCal = new AverageCalculator();
		List<Filter> emptyFilters = new LinkedList<>();
		List<AverageResult> results = avCal.calculateAverage(initResponses, emptyFilters);
		assertEquals(2.1, results.get(1).getAverage(), 0.01);
	}
	
	@Test
	public void calculateAverageWithFilterNoResult() {
		AverageCalculator avCal = new AverageCalculator();
		List<AverageResult> results = avCal.calculateAverage(initResponses, filters2);
		assertEquals(0, results.size());
	}
	@Test
	public void calculateAverageWithFilterCountAll() {
		AverageCalculator avCal = new AverageCalculator();
		List<AverageResult> results = avCal.calculateAverage(initResponses, filters1);
		assertEquals(10, results.get(0).getCount());
	}
	
	@Test
	public void calculateAverageWithMultipleValues() {
		AverageCalculator avCal = new AverageCalculator();
		for(int i = 0; i<5; i++) {
			initResponses.get(i).addSimpleAnswerByID(1, "2");
		}
		List<AverageResult> results = avCal.calculateAverage(initResponses, filters1);
		assertEquals(1.5, results.get(0).getAverage(),0.01);
	}
	
	
}

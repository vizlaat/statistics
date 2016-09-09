package vzsolt.statistics.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vzsolt.statistics.analyze.AverageCalculator;
import vzsolt.statistics.analyze.CorrelationAnalyzerWithCustomFilter;
import vzsolt.statistics.analyze.Response;
import vzsolt.statistics.utility.AverageResult;
import vzsolt.statistics.utility.CorrelationSettings;
import vzsolt.statistics.utility.Filter;

@Controller
@RequestMapping(value = "/analyze")
public class AnalyzeController {
	
	@Autowired
	private AverageCalculator avCal;
	
	@Autowired
	private CorrelationAnalyzerWithCustomFilter corrAnalyzer;

	public AnalyzeController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "settings")
	public String analyzeSettings() {
		return "analyze";
	}
	
	@RequestMapping(value = "average", method = RequestMethod.POST)
	@ResponseBody
	public List<AverageResult> calculateAverage(Model model, HttpSession session) {
		List<Response> responses;
		if(session.getAttribute("corrected") != null ) {
			responses = (List<Response>) session.getAttribute("corrected");
		} else {
			responses = (List<Response>) session.getAttribute("surveyResult");
		}
		List<Filter> filters = new ArrayList<>();
		if(session.getAttribute("filters") != null ) {
			filters = (List<Filter>) session.getAttribute("filters");
		}
		List<AverageResult> answers = avCal.calculateAverage(responses, filters);
		return answers;
		
	}
	
	@RequestMapping(value = "correlation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Double calculateCorrelation(Model model, HttpSession session, @RequestBody CorrelationSettings corrSettings) {
		List<Response> responses;
		if(session.getAttribute("corrected") != null ) {
			responses = (List<Response>) session.getAttribute("corrected");
		} else {
			responses = (List<Response>) session.getAttribute("surveyResult");
		}
		List<Filter> filters = new ArrayList<>();
		if(session.getAttribute("filters") != null ) {
			filters = (List<Filter>) session.getAttribute("filters");
		}
		corrAnalyzer.setFilters(filters);
		corrAnalyzer.setQuestionOneID(Integer.valueOf(corrSettings.getFieldOne()));
		corrAnalyzer.setQuestionTwoID(Integer.valueOf(corrSettings.getFieldTwo()));
		Double correlation = corrAnalyzer.analyze(responses);
		return correlation;
	}

}

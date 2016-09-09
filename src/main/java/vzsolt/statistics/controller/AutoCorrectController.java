package vzsolt.statistics.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vzsolt.statistics.analyze.Response;
import vzsolt.statistics.autocorrect.AutoCorrector;
import vzsolt.statistics.autocorrect.CorrectAnswer;

@Controller
@RequestMapping(value = "/autocorrect")
public class AutoCorrectController {
	
	@Autowired
	private AutoCorrector autoCorrector;
	

	public AutoCorrectController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "settings", method = RequestMethod.GET)
	public String autoCorrectSettings(Model model, HttpSession session) {
		session.removeAttribute("corrected");
		session.removeAttribute("correctAnswers");
		return "autocorrect";
	}

	@RequestMapping(value = "evaluation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<CorrectAnswer> autoCorrectDataBind(Model model, @RequestBody List<CorrectAnswer> correctAnswerW, HttpSession session) {
		System.out.print(correctAnswerW);
		session.setAttribute("correctAnswers", correctAnswerW);
		return correctAnswerW;
	}
	
	@RequestMapping(value = "evaluation/finalize", method = RequestMethod.GET)
	public String autoCorrectEvaulate(Model model, HttpSession session) {
		List<CorrectAnswer> correctAnswers = (List<CorrectAnswer>) session.getAttribute("correctAnswers");
		List<Response> surveyResult = (List<Response>) session.getAttribute("surveyResult");
		List<Response> corrected = autoCorrector.correctSurvey(surveyResult, correctAnswers);
		session.setAttribute("questions", corrected.get(0).getQuestionMap());
		session.setAttribute("corrected", corrected);
		return "autocorrect_result";
	}
	
	
	
}

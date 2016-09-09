package vzsolt.statistics.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vzsolt.statistics.utility.Filter;

@Controller
public class FilterSetupController {

	public FilterSetupController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/analyze/filter/submit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<Filter> setFilters(Model model, HttpSession session, @RequestBody List<Filter> filters) {
		session.setAttribute("filters", filters);
		return filters;
	}
	
	@RequestMapping(value = "/analyze/filter/remove", method = RequestMethod.POST,  produces = "application/json")
	@ResponseBody
	public String clearFilters(Model model, HttpSession session) {
		session.removeAttribute("filters");
		return "\"Filters are removed.\"";
	}

}

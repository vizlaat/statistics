package vzsolt.statistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/plot")
public class PlotController {

	public PlotController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "settings",method = RequestMethod.GET)
	public String plotHome() {
		return "plot";
	}
	
	

}

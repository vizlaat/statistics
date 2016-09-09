package vzsolt.statistics.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vzsolt.statistics.analyze.Response;
import vzsolt.statistics.analyze.SourceReader;
import vzsolt.statistics.utility.FileConverter;



@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	@Autowired
	private SourceReader sourceReader;

	@Autowired
	private FileConverter fileConverter;


	public UploadController() {
		// TODO Auto-generated constructor stub
	}


	
	@RequestMapping(method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model, HttpSession session) {
		if (!file.isEmpty()) {
			File converted;
			try {
				converted = fileConverter.convertMultipartFile(file);
				List<Response> surveyResult = sourceReader.readResponses(converted);
				session.setAttribute("questions", surveyResult.get(0).getQuestionMap());
				session.setAttribute("surveyResult", surveyResult);
				session.setAttribute("fileName", file.getOriginalFilename());
				List<Response> preview = new ArrayList<>();
				for(int i=0; i<5; i++) {
					preview.add(surveyResult.get(i));
				}
				model.addAttribute("preview", preview);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "preview";
	}



}

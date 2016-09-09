package vzsolt.statistics.analyze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jms.IllegalStateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;



@Service
public class SourceReader {
	@Autowired
	LineParser lineParser;
	private static final Logger logger = LoggerFactory.getLogger(SourceReader.class);
	

	public SourceReader() {
		// TODO Auto-generated constructor stub
	}

	public List<Response> readResponses(File file) {
		
		try ( CSVReader reader = new CSVReader(new InputStreamReader( new FileInputStream(file)))) {
			return readSource(reader);
		} catch (FileNotFoundException e) {
			
			logger.error("File not found!", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException occured!", e);
		}
		
		//if something went wrong
		return Collections.emptyList();

	}

	private List<Response> readSource(CSVReader reader) {
		// TODO Auto-generated method stub
		List<Response> responses = new ArrayList<>();
		try {
			String[] firstLine = reader.readNext();
			lineParser.setHeader(firstLine);;
			String[] line = reader.readNext();
			while (line != null) {
				logger.trace("Working with line: {}", line[0]);
				responses.add(lineParser.readLine(line));
				line = reader.readNext();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException occured!", e);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Reading the results is ended.");
		return Collections.unmodifiableList(responses);

	}

}

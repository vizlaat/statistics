package vzsolt.statistics.analyze;

import javax.jms.IllegalStateException;

import org.springframework.stereotype.Service;


@Service
public class LineParser {
	private String[] header;

	
	public LineParser() {
		
	}

	public LineParser(String[] header) {
		this.header = header;
	}
	
	
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	
	public Response readLine(String[] pieces) throws IllegalStateException {
		if(!(header.length> 0)) {
			throw new IllegalStateException("Missing header!");
		}
		if(!(header.length == pieces.length)) {
			throw new IllegalStateException("Header and answer do not match!");
		}
		Response response = new Response();
		for(int i=0; i < pieces.length; i++) {
			response.addSimpleAnswer(header[i], pieces[i]);
		}
		return response;
	}
	
	
}

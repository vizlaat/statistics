package vzsolt.statistics.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileConverter {

	public FileConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public File convertMultipartFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
		
	}

}

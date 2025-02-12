package edu.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UploadController {

	// 단일 파일을 upload 파일의 폴더에 저장 
	@RequestMapping(value="file/upload01.do" , method=RequestMethod.GET)
	public String uploadFile() {
		return "upload/file01";
	}
	
	
	
}
 
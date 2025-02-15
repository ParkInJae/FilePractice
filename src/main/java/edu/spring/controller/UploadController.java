package edu.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	// 단일 파일을 upload 파일의 폴더에 저장 
/*
 @GetMapping, @PostMapping, @PutMapping, @DeleteMapping을 사용하려면 Spring 4.3 이상부터 사용할 수 있음 
 */
	@GetMapping("/file/upload01")
	public String uploadFile() {
		return "upload/file01";
	}
	
	// post 요청
	//RESTful 방식
	@PostMapping(value="/file/upload01.do")
	public String uploadFile01(MultipartFile file01,HttpServletRequest request, String title, String content)throws IllegalStateException {
		
		return "";
	}
	
	
	
}
 
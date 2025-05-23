package edu.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller  // 만약 API응답(JSON 등)을 하고 싶다면 @RestController를 사용 , 그러나 jsp 뷰를 반환하므로@Controller를 사용함
// Spring에서 JSP와 같은 뷰 화면을 불러올 때는 @controller가 맞음
public class UploadController {
/*
 @GetMapping, @PostMapping, @PutMapping, @DeleteMapping을 사용하려면 Spring 4.3 이상부터 사용할 수 있음
 
 RESTful API 설계 기본 원칙 
 
HTTP 메서드 활용
GET: 데이터 조회
POST: 데이터 생성
PUT/PATCH: 데이터 수정
PUT >> 전체 내용을 수정할 때 사용 
PATCH >> 일부 내용을 수정할 때 사용
DELETE: 데이터 삭제
들어오는 URI는 명사로 표현 
  
 */
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
    // [GET] 단일 파일 업로드 폼 페이지 제공
    @GetMapping("/file/upload01")
    public String showSingleFileUploadForm() {
        // 업로드 폼 뷰(예: upload/file01.jsp)를 반환합니다.
        return "upload/file01";
    }
    
/*
	 1. @RequestMapping(value="/file/upload01.do, method=RequestMethod.POST)를 간결하게 @PostMappiong(value="file/upload01")로 간결하게 표현
	 2. IllegalStateException은 객체의 현재 상태가 메서드 호출에 적합하지 않은 경우 발생하는 예외이며, 주로 프로그래밍을 잘못 작성했을 대 발생함  
*/
    // [POST] 단일 파일 업로드 처리
    @PostMapping("/file/upload01")
    public String handleSingleFileUpload(MultipartFile file01,
                                         HttpServletRequest request,
                                         String title,
                                         String content) throws IllegalStateException, IOException {
    	    	    	
        // 업로드 폼에서 전달받은 제목과 내용을 출력
    	logger.info("{content}",  content);
    	logger.info("{title}",  title);
        
        // 업로드한 파일을 저장하기 위해 웹 애플리케이션 내의 '/resources/upload' 폴더의 실제 경로를 구합니다.
    	// 서버 내부의 경로에 저장 함 
        String path = request.getSession().getServletContext().getRealPath("/resources/upload");
        logger.info("{path}",path);
        
        // 저장 폴더가 없으면 생성합니다.
        // File 타입 메소드 정리 https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=saseo90&logNo=221267786511       
        File dir = new File(path);
        if (!dir.exists()) { // 정해진 경로가 없으면 새로 만들어라 
            dir.mkdirs();
        }
        
        // 파일이 존재하는지 확인한 후,
        // UUID를 이용해 파일명 중복을 방지하며 저장합니다.
        // new File(디렉토리 경로, 해당 디렉토리 내에서 파일 이름을 지정 )
        if (!file01.getOriginalFilename().isEmpty()) { // 만약 매개변누로 들어오는 MultiFile 타입의 file01이름이 비어있지 않다면
            UUID uuid = UUID.randomUUID();  // 랜덤으로 변환 
            String fileRealName = uuid.toString() + file01.getOriginalFilename(); // 랜덤 UUID와 기존 파일 이름을 결합하여 새로운 파일 이름 생성  
            file01.transferTo(new File(path, fileRealName)); // 새로운 파일 이름을 사용하여 지정된 path에 저장한다.
        }
        
        // 업로드 후 루트 경로로 리다이렉트(예: 메인 페이지)
        return "redirect:/";
    }

    
    
    
    
    // [GET] 다중 파일 업로드 폼 페이지 제공
    @GetMapping("/file/upload02")
    public String showMultipleFileUploadForm() {
        // 업로드 폼 뷰(예: upload/file02.jsp)를 반환합니다.
        return "upload/file02";
    }
    
    
    // [POST] 개별 파라미터로 전달된 다중 파일 업로드 처리
    @PostMapping("/file/upload02")
    public String handleMultipleFileUpload(MultipartFile file01,
                                           MultipartFile file02,
                                           MultipartFile file03,
                                           HttpServletRequest request) throws IOException {
        // 저장 경로 확인
        String path = request.getSession().getServletContext().getRealPath("/resources/upload");
        logger.info("{path}",path);
        
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        // 각 파일에 대해 파일이 첨부되어 있으면 UUID를 적용해 저장합니다.
        if (!file01.getOriginalFilename().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String fileRealName = uuid.toString() + file01.getOriginalFilename();
            file01.transferTo(new File(path, fileRealName));
        }
        
        if (!file02.getOriginalFilename().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String fileRealName = uuid.toString() + file02.getOriginalFilename();
            file02.transferTo(new File(path, fileRealName));
        }
        
        if (!file03.getOriginalFilename().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String fileRealName = uuid.toString() + file03.getOriginalFilename();
            file03.transferTo(new File(path, fileRealName));
        }
        
        return "redirect:/";
    }
    
    // [POST] 요청 파라미터로 리스트 형태의 다중 파일 업로드 처리
    @PostMapping("/file/upload03")
    public String handleMultiFileUpload(@RequestParam("multiFile") List<MultipartFile> multiFiles,HttpServletRequest request) throws IOException {
       // 저장할 위치 
    	// 서버 파일 시스템상의 실제 절대 경로를 반환.
    	/*
		// C:\tomcat\webapps\fileTest에 배포되어 있다면,getRealPath("/resources/upload")는 C:\tomcat\webapps\fileTest\resources\ upload와 같은 경로를 반환하고, 업로드된 파일은 해당 경로에 저장됩니다.
    	 */
    	String path = request.getSession().getServletContext().getRealPath("/resources/upload");
    	logger.info("{path}",path);
        
        // 만약 정해진 경로에 없다면, 새로운 디렉토리를 만든다. 
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        // 전달받은 파일 리스트의 각 파일에 대해 저장 처리
        for (MultipartFile file : multiFiles) {
            if (!file.getOriginalFilename().isEmpty()) {
                UUID uuid = UUID.randomUUID();
                String fileRealName = uuid.toString() + file.getOriginalFilename();
                file.transferTo(new File(path, fileRealName));
            }
        }
        
        return "redirect:/";
    }
}

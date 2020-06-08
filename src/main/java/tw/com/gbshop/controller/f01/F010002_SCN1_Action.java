package tw.com.gbshop.controller.f01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tw.com.core.controller.BaseAction;
import tw.com.gbshop.service.F010001Service;
import tw.com.gbshop.utils.MultipartFileToFile;

@Controller
@RequestMapping(value="/F010002_SCN1")
public class F010002_SCN1_Action extends BaseAction{
	private final static Logger logger = LoggerFactory.getLogger(F010002_SCN1_Action.class);
	@Autowired
	F010001Service f010001Service;
	
	String dataPath = getPropertis("dataPath");
	
	@RequestMapping()
	public ModelAndView index() {
		logger.debug("");
		ModelAndView mv = new ModelAndView("f01/F010002_SCN1");
		return mv;
	}
	
	@RequestMapping(value="/convertData")
	public ResponseEntity<byte[]> convertData(
//			@ModelAttribute("uploadForm") FileUploadForm uploadForm,
			@RequestParam CommonsMultipartFile file,
			HttpSession session) throws IOException {
		logger.debug("convertData");
		
//		String path=session.getServletContext().getRealPath("/");  
		String filename = "splite_"+file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		String tmpPath = dataPath + uuid + "\\";
		
		File tmpFile = MultipartFileToFile.toFile(file, tmpPath);
		tmpFile = f010001Service.convertFamiPDF(tmpFile, tmpPath);
		byte[] contents = Files.readAllBytes(tmpFile.toPath());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		return response;
	}
	
}

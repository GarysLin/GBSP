package tw.com.gbshop.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MultipartFileToFile {
	/**
	 * MultipartFile轉File
	 * @param multipartFile
	 * @param tmpPath
	 * @return 
	 */
	public static File toFile(MultipartFile multipartFile,String tmpPath) throws IOException {
		if(multipartFile.isEmpty()) return null;
			
		File f = new File(tmpPath); 
		if(!f.exists()) f.mkdirs();
		
		String filename = multipartFile.getOriginalFilename();
		String filepath = tmpPath + "/" + filename;
		File file = new File(filepath);
		
		CommonsMultipartFile commonsmultipartfile = (CommonsMultipartFile) multipartFile;
		commonsmultipartfile.transferTo(file);
		System.out.println("转换之后的文件："+file);
		
		return file;
	}
	
}

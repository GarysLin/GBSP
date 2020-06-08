package tw.com.gbshop.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class PdfToImage {
	private final static Logger logger = LoggerFactory.getLogger(PdfToImage.class);
	
	public static List<String> convertPDF(String pdfPath, String imagePath) {
		JSONObject params = new JSONObject();
		params.put("pdfPath", pdfPath);
		params.put("imagePath", imagePath);
		logger.debug(params.toString()); 
		
		File file = new File(pdfPath);
		return convertPDF(file, imagePath);
	}
	
	public static List<String> convertPDF(File file, String imagePath) {
		JSONObject params = new JSONObject();
		params.put("file", file);
		params.put("imagePath", imagePath);
		logger.debug(params.toString()); 
		
		List<String> imageList = new ArrayList<String>();
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for(int i = 0; i < pageCount; i++){
				BufferedImage image = renderer.renderImageWithDPI(i, 296);
//				BufferedImage image = renderer.renderImage(i, 2.5f);
				String s = imagePath + i + ".png";
				imageList.add(s);
				ImageIO.write(image, "PNG", new File(s));
			}
		} catch (IOException e) {
			logger.error("Class exception: " + e);
			e.printStackTrace();
		}
		return imageList;
	}
	
}

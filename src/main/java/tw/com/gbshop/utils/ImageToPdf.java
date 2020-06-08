package tw.com.gbshop.utils;

import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
 
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName imageToPdf
 * @Description 多張圖轉PDF
 * @Author Gart.lin
 * @Date 2020/5/27
 **/
public class ImageToPdf {
	private final static Logger logger = LoggerFactory.getLogger(ImageToPdf.class);
	
	/**
	 * 將路徑下圖片合併轉PDF
	 * @param imageFolderPath 圖片資料夾路徑
	 * @param pdfPath PDF儲存位置
	 */
	public static File toPdf(String imageFolderPath, String pdfPath) {
		JSONObject params = new JSONObject();
		params.put("imageFolderPath", imageFolderPath);
		params.put("pdfPath", pdfPath);
		logger.debug(params.toString()); 
		
		try {
			FileOutputStream fos = new FileOutputStream(pdfPath);
			Document doc = new Document(null, 0, 0, 0, 0);
			PdfWriter.getInstance(doc, fos);
			File file = new File(imageFolderPath);
			File[] files = file.listFiles();
			for (File file1 : files) {
				if (file1.getName().endsWith(".png") || file1.getName().endsWith(".jpg") || file1.getName().endsWith(".gif")
						|| file1.getName().endsWith(".jpeg") || file1.getName().endsWith(".tif")) {
					String imagePath = imageFolderPath + file1.getName();
					System.out.println(imagePath);
					BufferedImage img = ImageIO.read(new File(imagePath));
					doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
//					doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
					Image image = Image.getInstance(imagePath);
					doc.open();
					doc.add(image);
				}
			}
			doc.close();
		} catch (Exception e) {
			logger.error("Class exception: " + e);
			e.printStackTrace();
		}
		return new File(pdfPath);
	}
	
//	public static void main(String[] args) {
//		String imageFolderPath = "C:\\Gary\\Work\\workspace\\myworkspace\\GBSP\\src\\main\\webapp\\images\\tmp\\";
//		String pdfPath = "C:\\Gary\\Work\\workspace\\myworkspace\\GBSP\\src\\main\\webapp\\images\\total.pdf";
//		ImageToPdf.toPdf(imageFolderPath, pdfPath);
//	}
}

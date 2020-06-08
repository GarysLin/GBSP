package tw.com.gbshop.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import tw.com.gbshop.utils.ImageToPdf;
import tw.com.gbshop.utils.PdfToImage;

@Service
public class F010001Service {
	private final static Logger logger = LoggerFactory.getLogger(F010001Service.class);
	
	public File convertFamiPDF(File file, String tmpPath) throws IOException {
		JSONObject params = new JSONObject();
		params.put("file", file);
		params.put("tmpPath", tmpPath);
		logger.debug(params.toString()); 
		
		String splitImgPath = tmpPath + "temp\\";
		String pdfPath = tmpPath + "return.pdf";
		
		File f = new File(splitImgPath);
		if(!f.exists()) f.mkdirs();
		
		List<String> imageList = PdfToImage.convertPDF(file, tmpPath);
		splitFamiImage(imageList, splitImgPath);
		File returnFile = ImageToPdf.toPdf(splitImgPath, pdfPath);
		
		logger.debug("delete start");
		String[]entries = f.list();
		for(String s: entries){
			File currentFile = new File(f.getPath(),s);
			currentFile.delete();
		}
		f.delete();
		for(String img: imageList) {
			File dfile = new File(img);
			boolean d = dfile.delete();
			System.out.println(d);
		}
		logger.debug("delete over");
		return returnFile;
	}
	
	
	private void splitFamiImage(List<String> imgList, String image_Path) throws IOException {
		JSONObject params = new JSONObject();
		params.put("imgList", imgList);
		params.put("image_Path", image_Path);
		logger.debug(params.toString()); 
		BufferedImage imgs[] = new BufferedImage[imgList.size() * 4];
		int count = 0;
		for(int i = 0; i < imgList.size(); i++) {
			File file = new File(imgList.get(i));
			FileInputStream fis = new FileInputStream(file);
			BufferedImage image = ImageIO.read(fis);
			
			String[][] imageSplit = {
					{"260", "25", "1218", "1595"},
					{"1222", "25", "2173", "1595"},
					{"260", "1601", "1218", "3160"},
					{"1222", "1601", "2173", "3160"}
				};
			
			for(String[] l : imageSplit) {
				int x1 = Integer.parseInt(l[0]);
				int y1 = Integer.parseInt(l[1]);
				int x2 = Integer.parseInt(l[2]);
				int y2 = Integer.parseInt(l[3]);
				
				int width = x2 - x1;
				int hight = y2 - y1;
				
				imgs[count] = new BufferedImage(width, hight, image.getType());

				Graphics2D gr = imgs[count++].createGraphics();
				gr.drawImage(image, 0, 0,
						width, hight,
						x1, y1,
						x2, y2, null);
				gr.dispose();
			}
			
		}
		
		
		for (int i = 0; i < imgs.length; i++) {
			ImageIO.write(imgs[i], "jpg", new File(image_Path + "\\temp" + i + ".jpg"));
		}

		System.out.println("完成分割！");
	}
	
	
//	public static void main(String[] args) {
//		F010001Service f = new F010001Service();
//		
//		String pdfPath = basePath + "01\\";
//		String imagePath = basePath + " ";
//		String[] imgList = {"pdfbox_image0.png"};
//		try {
//			PdfToImage.convertPDF(pdfPath, imagePath);
//			f.splitFamiImage(imgList, basePath+"tmp\\");
////			F010001Service.splitImage();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}

package tw.com.gbshop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName DataZIP
 * @Description 壓縮檔處理
 * @Author Gart.lin
 * @Date 2020/5/27
 **/
public class DataZIP {

	/**
	* 功能:壓縮多個檔案成一個zip檔案
	* @param srcfile：原始檔列表
	* @param zipfile：壓縮後的檔案
	*/
	public static void zipFiles(File[] srcfile,File zipfile){
		byte[] buf=new byte[1024];
		try {
			//ZipOutputStream類：完成檔案或資料夾的壓縮
			ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipfile));
			for(int i = 0; i < srcfile.length; i++){
				FileInputStream in=new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while((len=in.read(buf))>0){
					out.write(buf,0,len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			System.out.println("壓縮完成.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("壓縮失敗.");
		}
	}
	/**
	* 功能:解壓縮
	* @param zipfile：需要解壓縮的檔案
	* @param descDir：解壓後的目標目錄
	*/
	public static void unZipFiles(File zipfile,String descDir){
		try {
			ZipFile zf=new ZipFile(zipfile);
			for(Enumeration<? extends ZipEntry> entries=zf.entries();entries.hasMoreElements();){
				ZipEntry entry=(ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in=zf.getInputStream(entry);
				OutputStream out=new FileOutputStream(descDir+zipEntryName);
				byte[] buf1=new byte[1024];
				int len;
				while((len=in.read(buf1))>0){
					out.write(buf1,0,len);
				}
				in.close();
				out.close();
				System.out.println("解壓縮完成.");
			}
			zf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String basePath = "C:\\Gary\\Work\\workspace\\myworkspace\\GBSP\\src\\main\\webapp\\images\\";
		//2個原始檔
		File f1=new File(basePath + "\\temp0.jpg");
		File f2=new File(basePath + "\\temp1.jpg");
		File f3=new File(basePath + "\\temp2.jpg");
		File f4=new File(basePath + "\\temp3.jpg");
		File[] srcfile={f1, f2, f3, f4};
		//壓縮後的檔案
		File zipfile=new File(basePath + "\\data.zip");
		DataZIP.zipFiles(srcfile, zipfile);
//		//需要解壓縮的檔案
//		File file=new File("D:\\workspace\\flexTest\\src\\com\\biao\\test\\biao.zip");
//		//解壓後的目標目錄
//		String dir="D:\\workspace\\flexTest\\src\\com\\biao\\test\\";
//		DataZIP.unZipFiles(file, dir);
		
	}

}

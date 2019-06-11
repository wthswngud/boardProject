package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartUtil {

	private static final String UPLOAD_PATH = "d:\\upload\\";

	/**
	* Method : getFileName
	* 작성자 : PC19
	* 변경이력 :
	* @param contentDisposition
	* @return
	* Method 설명 : contentDisposition에서 파일명을 획득, 반환한다.
	*/
	public static String getFileName(String contentDisposition) {
		// form-data; name="profile"; filename="Tulips.jpg"
		//파일명만 빼내자
		
		String[] splited = contentDisposition.split("; ");
		
		for(String split : splited){
			if(split.startsWith("filename=")){
				int startIndex = split.indexOf("\"")+1;
				int lastIndex = split.lastIndexOf("\"");
				return split.substring(startIndex, lastIndex);
			}
		}
		
		return "";
	}

	/**
	* Method : getExt
	* 작성자 : PC19
	* 변경이력 :
	* @param fileName
	* @return
	* Method 설명 : 파일명으로부터 파일 확장자를 반환
	*/
	public static String getExt(String fileName) {
		String ext = "";
		String[] splited = fileName.split("[.]");
		if(splited.length!=1)
			ext = splited[splited.length-1];
		
//		int startIndex = fileName.lastIndexOf(".");
//		int lastIndex = fileName.length();
		
//		String result = fileName.substring(startIndex, lastIndex);
		
		return ext.equals("") ? "" : "." + ext;
	}
	
	/**
	* Method : checkUploadFolder
	* 작성자 : PC19
	* 변경이력 :
	* @param yyyy
	* @param mm
	* Method 설명 :년, 월 업로드 폴더가 존재하는지 체크, 없을 경우 폴더 생성
	*/
	public static void checkUploadFolder(String yyyy, String mm){
		File yyyyFolder = new File(UPLOAD_PATH+yyyy);
		//신규년도로 넘어갔을때 해당 년도의 폴더를 생성한다.
		if(!yyyyFolder.exists())
			yyyyFolder.mkdir();
		
		//월에 해당하는 폴더가 있는지 확인
		File mmFolder = new File(UPLOAD_PATH+ yyyy + File.separator + mm);
		if(!mmFolder.exists())
			mmFolder.mkdir();
	}
	
	
	/**
	* Method : getUploadPath
	* 작성자 : PC19
	* 변경이력 :
	* @param yyyy
	* @param mm
	* @return
	* Method 설명 : 업로드 경로를 반환
	*/
	public static String getUploadPath(){
		//년도에 해당하는 폴더가 있는지, 년도안에 월에 해당하는 폴더가 있는지 확인 하는 작업
		Date dt = new Date();
		SimpleDateFormat yyyySdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat mmSdf = new SimpleDateFormat("MM");
		String yyyy = yyyySdf.format(dt);	// 현재 년도 추출
		String mm = mmSdf.format(dt);		// 현재 월 추출
		
		checkUploadFolder(yyyy, mm);
		return UPLOAD_PATH + yyyy + File.separator + mm;
	}
}

package cn.hanyuweb.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadUtil {

	public static List<String> uploadImages(MultipartFile[] files) {
		List<String> list=new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			if(!files[i].isEmpty()){
				MultipartFile file=files[i];
				String originalFilename = file.getOriginalFilename();
				String filename=System.currentTimeMillis()+originalFilename;
				list.add(filename);
				try {
					file.transferTo(new File(LoadPropertyUtil.load("imagePath.properties","image_windowPath")+"\\"+filename));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

	public static void deleteImages(String url){
		File file= new File(LoadPropertyUtil.load("imagePath.properties","image_windowPath")+"\\"+url);
		file.delete();
	}
}

package com.sbbi.obesity.test;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbbi.obesity.helpers.ImageHelper;
import com.sbbi.obesity.manager.ClassificationManager;
import com.sbbi.obesity.manager.UploadedImageManager;
import com.sbbi.obesity.model.Pixels;
import com.sbbi.obesity.model.Prediction;

@RestController
@RequestMapping("/test")
public class ControllerTest {

	@RequestMapping(method = RequestMethod.GET)
	public void upload() {
		
		String path = "/home/bsilva/Desktop/sbbi/obesityApp/images/5/1498078947301/1.jpg";
		path = "/home/bsilva/Desktop/krai/1.jpg";
		UploadedImageManager manager = new UploadedImageManager();
		Pixels pixels = manager.getRelationTopImage(path);
		System.out.println("done");
		
	}
	
}

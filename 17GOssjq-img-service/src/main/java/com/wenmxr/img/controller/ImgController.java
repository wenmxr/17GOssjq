package com.wenmxr.img.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wenmxr.img.service.ImgService;
import com.wenmxr.vo.PicUploadResult;

@RestController
@RequestMapping("/uploadImg/pic")
public class ImgController {

	@Autowired
	private ImgService imgService = null;
	
	@RequestMapping("/upload")
	public PicUploadResult imgUpload(MultipartFile pic) {
		System.out.println("1111111");
		return imgService.imgUpload(pic);
	}
}

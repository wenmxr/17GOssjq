package com.wenmxr.img.service;

import com.wenmxr.utils.UploadUtil;
import com.wenmxr.vo.PicUploadResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class ImgService {

	public PicUploadResult imgUpload(MultipartFile pic) {
		/**
		 * 1.获取验证文件的后缀 .jpg .png .gif .jpeg
		 * 	如果判断不是合法后缀直接返回错误数据
		 * 2.生成一个多级路径 	/upload/1/d/3/d/3/d/f/g/
		 *  根据图片名称进行hash字符串计算 8个字符
		 * 3.创建图片的逻辑目录
		 * 4.重命名文件 uuid+后缀
		 * 5.流数据输出到文件夹中 图片上传完毕
		 * 6.根据上传成功后的路径，生成的http的url地址返回给ajax
		 */
		// 准备一个返回结果
		PicUploadResult result = new PicUploadResult();
		try {
			// 拿到源文件名称
			String oFileName = pic.getOriginalFilename();
			System.out.println(oFileName);
			// 截取后缀 
			String extName = oFileName.substring(oFileName.lastIndexOf("."));
			// 正则表达式判断 
			if (!extName.matches(".(jpg|png|gif|jpeg)$")) {
				result.setError(1);
				return result;
			}
			// 使用common中的工具类UploadUtil
			String dir = UploadUtil.getUploadPath(oFileName, "17go");
			// 使用file创建多级目录 
			String path = "/Users/xuming/Desktop/17GO/img/" + dir + "/";
			File _dir = new File(path);
			// 判断文件夹是否已存在，不存在则创建
			if (!_dir.exists()) {// 需要创建多级
				_dir.mkdirs();
			}
			// 重命名文件
			//dd135a52-3361-4a4f-ad68-79e5826152ab.jpg
			String newFileName = UUID.randomUUID().toString() + extName;
			// 将图片流输到 path+newFileName
			pic.transferTo(new File(path + newFileName));
			// 生成一个可访问图片的URL地址
			String url = "http://image.jt.com:8080/" + dir + "/" + newFileName;
			result.setUrl(url);
			return result;
		} catch (Exception e) {// 发现异常图片上传失败
			e.printStackTrace();
			result.setError(1);
			return result;
		}
	}
	
}

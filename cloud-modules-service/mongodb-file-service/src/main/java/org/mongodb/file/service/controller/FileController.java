package org.mongodb.file.service.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.cloud.service.tools.md5.MD5Util;
import org.mongodb.file.service.entity.File;
import org.mongodb.file.service.service.FileService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月12日
 * @description: FileController
 */
@RestController
@RequestMapping("/file")
public class FileController {

	@Resource
	private FileService fileService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月30日
	 * @param id
	 * @return {@link ResponseEntity}
	 * @throws UnsupportedEncodingException
	 * @description: 下载文件
	 */
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String id) throws UnsupportedEncodingException {
		File file = fileService.getFileById(id);
		if (file != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentLength(file.getSize());
			headers.setConnection("close");
			return new ResponseEntity<byte[]>(file.getContent(), headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月30日
	 * @param id
	 * @return {@link ResponseEntity}
	 * @throws UnsupportedEncodingException
	 * @description: 预览文件
	 */
	@GetMapping("/view/{id}")
	public ResponseEntity<byte[]> viewFile(@PathVariable String id) {
		File file = fileService.getFileById(id);
		if (file != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("inline").build());
			headers.setContentType(MediaType.parseMediaType(file.getContentType()));
			headers.setContentLength(file.getSize());
			headers.setConnection("close");
			return new ResponseEntity<byte[]>(file.getContent(), headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月30日
	 * @param file
	 * @return {@link JsonApi}
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @description: 文件上传
	 */
	@PostMapping("/upload")
	public JsonApi<?> uploadFile(@RequestParam("file") MultipartFile file) throws NoSuchAlgorithmException, IOException {
		File returnFile = null;
		File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
		f.setMd5(MD5Util.getInstance().getFileMD5(file.getInputStream()));
		returnFile = fileService.saveFile(f);
		if (returnFile != null) {
			return new JsonApi<String>(ApiCodeEnum.OK, returnFile.getId());
		}
		return new JsonApi<>(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月30日
	 * @param id
	 * @return {@link JsonApi}
	 * @description: 文件删除
	 */
	@DeleteMapping("/delete/{id}")
	public JsonApi<?> deleteFile(@PathVariable String id) {
		fileService.removeFile(id);
		return new JsonApi<>(ApiCodeEnum.OK);
	}
}
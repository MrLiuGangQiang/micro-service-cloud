package org.mongodb.file.service.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.mongodb.file.service.dao.FileRepository;
import org.mongodb.file.service.entity.File;
import org.springframework.stereotype.Service;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月12日
 * @description: FileService
 */
@Service
public class FileService {
	@Resource
	private FileRepository fileRepository;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月30日
	 * @param file
	 * @return {@link File}
	 * @description: 保存文件
	 */
	public File saveFile(File file) {
		return fileRepository.save(file);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月30日
	 * @param id
	 * @description: 根据ID删除文件
	 */
	public void removeFile(String id) {
		fileRepository.deleteById(id);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月30日
	 * @param id
	 * @return {@link File}
	 * @description: 根据ID获取文件
	 */
	public File getFileById(String id) {
		Optional<File> op = fileRepository.findById(id);
		return op.isPresent() ? op.get() : null;
	}
}

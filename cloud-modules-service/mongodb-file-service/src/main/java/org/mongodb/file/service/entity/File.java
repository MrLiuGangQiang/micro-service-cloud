package org.mongodb.file.service.entity;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月12日
 * @description: 文件实体类
 */
@Document
public class File {
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @description: 主键
	 */
	@Id
	private String id;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @description: 文件名称
	 */
	private String name;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @description: 文件类型
	 */
	private String contentType;
	/**
	 * @type: {@link long}
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @description: 文件大小
	 */
	private long size;
	/**
	 * @type: {@link Date}
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @description: 上传日期
	 */
	private Date uploadDate;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @description: MD5校验码
	 */
	private String md5;
	/**
	 * @type: {@link byte[]}
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @description: 文件内容
	 */
	private byte[] content;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @description: 文件路径
	 */
	private String path;

	protected File() {
	}

	public File(String name, String contentType, long size, byte[] content) {
		this.name = name;
		this.contentType = contentType;
		this.size = size;
		this.uploadDate = new Date();
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		File fileInfo = (File) object;
		return Objects.equals(size, fileInfo.size) && Objects.equals(name, fileInfo.name) && Objects.equals(contentType, fileInfo.contentType) && Objects.equals(uploadDate, fileInfo.uploadDate) && Objects.equals(md5, fileInfo.md5)
				&& Objects.equals(id, fileInfo.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, contentType, size, uploadDate, md5, id);
	}

	@Override
	public String toString() {
		return "File{" + "name='" + name + '\'' + ", contentType='" + contentType + '\'' + ", size=" + size + ", uploadDate=" + uploadDate + ", md5='" + md5 + '\'' + ", id='" + id + '\'' + '}';
	}
}
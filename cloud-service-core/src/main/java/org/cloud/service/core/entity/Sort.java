package org.cloud.service.core.entity;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月10日
 * @description: 排序辅助类
 */
public class Sort {

	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 字段名
	 */
	private String field;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 排序规则 默认ASC
	 */
	private String direction;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDirection() {
		if (direction != null && ("ASC".equalsIgnoreCase(direction) || "DESC".equalsIgnoreCase(direction))) {
			return direction;
		} else {
			return "ASC";
		}
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}

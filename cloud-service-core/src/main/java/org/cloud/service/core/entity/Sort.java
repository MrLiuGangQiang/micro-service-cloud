package org.cloud.service.core.entity;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.entity
 * @remark sort entity
 */
public class Sort {

	/**
	 * sort field
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @type {@link String}
	 */
	private String field;
	/**
	 * sort direction default rule asc
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @type {@link String}
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

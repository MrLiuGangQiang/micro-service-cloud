package org.module.web.security.entity;

import org.cloud.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月17日
 * @description: 用户权限
 */
public class UserSecurity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Integer uid;
	private Integer oid;
	private String target;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}

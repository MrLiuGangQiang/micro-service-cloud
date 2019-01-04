package org.module.web.security.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.module.web.security.dao.UserSecurityMapper;
import org.module.web.security.entity.UserSecurity;
import org.module.web.security.global.BaseGlobal;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月17日
 * @description: UserSecurityService
 */
@Service
public class UserSecurityService {
	@Resource
	private UserSecurityMapper userSecurityMapper;

	@Cacheable(value = BaseGlobal.CACHE_WEB_USER, key = "'operation:'+#security.uid+':'+#security.oid", condition = "#security.uid ne null and #security.oid ne null", unless = "#result.size() le 0")
	public List<Map<String, Object>> getOperationList(UserSecurity security) {
		return userSecurityMapper.getOperationList(security);
	}

	@Cacheable(value = BaseGlobal.CACHE_WEB_USER, key = "'permission:'+#security.uid+':'+#security.oid", condition = "#security.uid ne null and #security.oid ne null", unless = "#result.size() le 0")
	public List<Map<String, Object>> getPermissionList(UserSecurity security) {
		return userSecurityMapper.getPermissionList(security);
	}

	@Cacheable(value = BaseGlobal.CACHE_WEB_USER, key = "'role:'+#security.uid+':'+#security.oid", condition = "#security.uid ne null and #security.oid ne null", unless = "#result.size() le 0")
	public List<Map<String, Object>> getRoleList(UserSecurity security) {
		return userSecurityMapper.getRoleList(security);
	}

	public Map<String, Object> getUserByPhoneAndPassword(String phone, String password) {
		return userSecurityMapper.getUserByPhoneAndPassword(phone, password);
	}
}

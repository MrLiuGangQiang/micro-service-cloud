package org.module.web.security.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cloud.service.core.mapper.IBaseMapper;
import org.module.web.security.entity.UserSecurity;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月17日
 * @description: UserSecurityMapper
 */
@Mapper
public interface UserSecurityMapper extends IBaseMapper<UserSecurity> {

	List<Map<String, Object>> getOperationList(UserSecurity security);

	List<Map<String, Object>> getPermissionList(UserSecurity security);

	List<Map<String, Object>> getRoleList(UserSecurity security);

	Map<String, Object> getUserByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);
}
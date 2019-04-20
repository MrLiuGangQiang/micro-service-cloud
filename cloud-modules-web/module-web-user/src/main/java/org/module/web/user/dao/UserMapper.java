package org.module.web.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.cloud.service.core.mapper.IBaseMapper;
import org.module.web.user.entity.User;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月17日
 * @description: UserMapper
 */
@Mapper
public interface UserMapper extends IBaseMapper<User,Integer> {
}
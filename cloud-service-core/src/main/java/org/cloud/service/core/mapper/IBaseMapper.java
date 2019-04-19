package org.cloud.service.core.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.cloud.service.core.entity.BaseEntity;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.mapper
 * @remark base mapper entity extends {@link BaseEntity}
 */
public abstract interface IBaseMapper<T extends BaseEntity, PK extends Serializable> {

	/**
	 * insert by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Integer}
	 */
	abstract int insert(T entity);

	/**
	 * delete by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Integer}
	 */
	abstract int delete(T entity);

	/**
	 * delete by single entity id
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param id
	 * @return {@link Integer}
	 */
	abstract int deleteById(PK id);

	/**
	 * update by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Integer}
	 */
	abstract int update(T entity);

	/**
	 * query map by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Map}
	 */
	abstract Map<String, Object> getOne(T entity);

	/**
	 * query map by single entity id
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param id
	 * @return {@link Map}
	 */
	abstract Map<String, Object> getOneById(PK id);

	/**
	 * query single entity by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link T}
	 */
	abstract T getOneForEntity(T entity);

	/**
	 * query single entity by single entity id
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link T}
	 */
	abstract T getOneForEntityById(PK id);

	/**
	 * query list by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link List}
	 */
	abstract List<Map<String, Object>> getList(T entity);

	/**
	 * query list entity by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link List}
	 */
	abstract List<T> getListForEntity(T entity);

	/**
	 * check repeat by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Map}
	 */
	abstract Map<String, Object> getRepeat(T entity);

	/**
	 * check repeat by single entity
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link T}
	 */
	abstract T getRepeatForEntity(T entity);
}
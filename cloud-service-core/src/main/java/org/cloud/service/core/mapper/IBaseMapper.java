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
 * @remark base mapper<br>
 *         entity extends {@link BaseEntity} <br>
 *         pk implements {@link Serializable}
 */
public abstract interface IBaseMapper<T extends BaseEntity, PK extends Serializable> {

	/**
	 * insert single object by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Integer}
	 */
	abstract int insert(T entity);

	/**
	 * delete records by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Integer}
	 */
	abstract int delete(T entity);

	/**
	 * delete single record by single instance primary key
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param id
	 * @return {@link Integer}
	 */
	abstract int deleteByPrimaryKey(PK id);

	/**
	 * update records by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Integer}
	 */
	abstract int update(T entity);

	/**
	 * query single record to map by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Map}
	 */
	abstract Map<String, Object> queryOne(T entity);

	/**
	 * query single record to map by single instance primary key
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param id
	 * @return {@link Map}
	 */
	abstract Map<String, Object> queryOneByPrimaryKey(PK id);

	/**
	 * query single record to entity by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link T}
	 */
	abstract T queryOneForEntity(T entity);

	/**
	 * query single record to entity by single instance primary key
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link T}
	 */
	abstract T queryOneForEntityByPrimaryKey(PK id);

	/**
	 * query multiple records to list>map by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link List}
	 */
	abstract List<Map<String, Object>> queryList(T entity);

	/**
	 * query multiple records to list>entity by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link List}
	 */
	abstract List<T> queryListForEntity(T entity);

	/**
	 * query repeat by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link Map}
	 */
	abstract Map<String, Object> queryRepeat(T entity);

	/**
	 * query repeat by single instance
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param entity
	 * @return {@link T}
	 */
	abstract T queryRepeatForEntity(T entity);
}
package org.cloud.service.core.valid;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.valid
 * @remark valided group
 */
public interface Valided {
	public interface Insert {};
	public interface Delete {};
	public interface DeleteByPrimaryKey {};
	public interface Update {};
	public interface QueryOne {};
	public interface QueryOneByPrimaryKey {};
	public interface QueryOneForEntity {};
	public interface QueryOneForEntityByPrimaryKey {};
	public interface QueryList {};
	public interface QueryListForEntity {};
	public interface QueryRepeat {};
	public interface QueryRepeatForEntity {};
}

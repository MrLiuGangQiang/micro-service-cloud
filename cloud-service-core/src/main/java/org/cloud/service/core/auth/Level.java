package org.cloud.service.core.auth;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.auth
 * @remark rbac permission level<br>
 */
public enum Level {

	/**
	 * role can contain one or more permission
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @type {@link Level}
	 */
	ROLE,
	/**
	 * permission can contain one or more operation
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @type {@link Level}
	 */
	PERMISSION,
	/**
	 * operation current minimum authority
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @type {@link Level}
	 */
	OPERATION
}

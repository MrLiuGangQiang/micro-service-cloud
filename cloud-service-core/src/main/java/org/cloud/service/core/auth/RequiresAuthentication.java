package org.cloud.service.core.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.auth
 * @remark rbac model auth<br>
 *         control priority order：<br>
 *         ignore-->authc-->role-->permission-->operation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiresAuthentication {

	/**
	 * rabc permission level default {@link Level#OPERATION} <br>
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @return {@link Level}
	 */
	Level level() default Level.OPERATION;

	/**
	 * permission value default is ""<br>
	 * recommended specification: [level][:module][:object][:operation]<br>
	 * for example<br>
	 * role:user:manager | perms:user:user:select | oper:user:user:user:insert
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @return {@link String}
	 */
	String value() default "";

	/**
	 * is neglect checking default false
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @return {@link Boolean}
	 */
	boolean ignore() default false;

	/**
	 * is login checking default false
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @return {@link Boolean}
	 */
	boolean authc() default false;
}

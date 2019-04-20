package org.cloud.service.core.session;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.session
 * @remark terminal enum
 */
public enum TerminalEnum {
	/**
	 * web
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link TerminalEnum}
	 */
	WEB("web"),
	/**
	 * android
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link TerminalEnum}
	 */
	Android("android"),
	/**
	 * ios
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link TerminalEnum}
	 */
	IOS("ios"),
	/**
	 * ios
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link TerminalEnum}
	 */
	BOX("box"),
	/**
	 * mobile contain android and ios
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link TerminalEnum}
	 */
	MOBILE("mobile"),
	/**
	 * wehcat
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link TerminalEnum}
	 */
	WECHAT("wechat");
	private String flag;

	public String getFlag() {
		return flag;
	}

	TerminalEnum(String falg) {
		this.flag = falg;
	}
}
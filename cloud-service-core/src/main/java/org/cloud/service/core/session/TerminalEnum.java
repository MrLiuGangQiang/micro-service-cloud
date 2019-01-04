package org.cloud.service.core.session;


/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: 终端枚举类
 */
public enum TerminalEnum {
	WEB("web"), Android("android"), IOS("ios"), BOX("box"), MOBILE("mobile"), WECHAT("wechat");
	private String flag;

	public String getFlag() {
		return flag;
	}

	TerminalEnum(String falg) {
		this.flag = falg;
	}
}
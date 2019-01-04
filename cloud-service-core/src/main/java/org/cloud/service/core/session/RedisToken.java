package org.cloud.service.core.session;

import java.io.Serializable;
import java.util.Base64;
import java.util.UUID;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: RedisToken
 */
public class RedisToken implements Serializable{

	private static final long serialVersionUID = -2246189231115595727L;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param terminal
	 * @param identity
	 * @return {@link String}
	 * @description: 创建token
	 */
	public static String createToken(TerminalEnum terminal, Object identity) {
		StringBuffer token = new StringBuffer();
		token.append(terminal.getFlag()).append(":");
		token.append(UUID.randomUUID()).append(":");
		token.append(identity.toString());
		return Base64.getEncoder().encodeToString(token.toString().getBytes());
	};

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param token
	 * @return {@link String}
	 * @throws Exception
	 * @description: 解密token
	 */
	public static String decodeToekn(String token) {
		String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
		if (token.matches(base64Pattern)) {
			return new String(Base64.getDecoder().decode(token.getBytes()));
		} else {
			throw new RuntimeException("It's not legal token");
		}
	}
}

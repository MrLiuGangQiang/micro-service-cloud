package org.cloud.service.core.session;

import java.io.Serializable;
import java.util.Base64;
import java.util.UUID;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.session
 * @remark redis token
 */
public class RedisToken implements Serializable {
	private static final long serialVersionUID = 1L;

	private static RedisToken instance;

	public static RedisToken getInstance() {
		if (instance == null) {
			instance = new RedisToken();
		}
		return instance;
	}

	private RedisToken() {
	}

	/**
	 * create token by {@link TerminalEnum} and instance primary key
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param terminal
	 * @param identity
	 * @return {@link String}
	 */
	public String createToken(TerminalEnum terminal, String identity) {
		StringBuffer token = new StringBuffer();
		token.append(terminal.getFlag()).append(":");
		token.append(UUID.randomUUID()).append(":");
		token.append(identity);
		return Base64.getEncoder().encodeToString(token.toString().getBytes());
	};

	/**
	 * create token by {@link TerminalEnum} and instance primary key
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param terminal
	 * @param identity
	 * @return {@link String}
	 */
	public String createToken(TerminalEnum terminal, int identity) {
		StringBuffer token = new StringBuffer();
		token.append(terminal.getFlag()).append(":");
		token.append(UUID.randomUUID()).append(":");
		token.append(identity);
		return Base64.getEncoder().encodeToString(token.toString().getBytes());
	};

	/**
	 * decode toekn
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param token
	 * @return {@link String}
	 */
	public String decodeToekn(String token) {
		String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
		if (token.matches(base64Pattern)) {
			return new String(Base64.getDecoder().decode(token.getBytes()));
		} else {
			throw new RuntimeException("it's not legal token");
		}
	}
}

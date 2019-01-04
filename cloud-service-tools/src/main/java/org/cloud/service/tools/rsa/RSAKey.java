package org.cloud.service.tools.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;


/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: TODO
 */
public class RSAKey {
	/**
	 * @type: {@link PublicKey}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 公钥
	 */
	private PublicKey publicKey;
	/**
	 * @type: {@link PrivateKey}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 私钥
	 */
	private PrivateKey privateKey;

	public RSAKey() {
	}

	public RSAKey(PublicKey publicKey, PrivateKey privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

}

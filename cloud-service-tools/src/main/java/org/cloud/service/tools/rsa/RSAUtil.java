package org.cloud.service.tools.rsa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import net.iharder.Base64;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: RSA加密解密工具类
 */
public class RSAUtil {
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 密匙生成算法
	 */
	public static final String KEY_ALGORITHM = "RSA";
	/**
	 * @type: {@link int}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * @type: {@link int}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 屏蔽默认构造器
	 */
	private RSAUtil() {
	}

	/**
	 * Copyright © 2018 Fist Team. All rights reserved.
	 *
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 内部静态类
	 */
	private static class RSAUtilInstance {
		private static final RSAUtil INSTANCE = new RSAUtil();
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @return {@link RSAUtil}
	 * @description: 内部静态方法
	 */
	public static RSAUtil getInstance() {
		return RSAUtilInstance.INSTANCE;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param publicKey
	 * @return {@link String}
	 * @description: 转换公钥为字符串(Java)
	 */
	public static String publicKeyToString(RSAPublicKey publicKey) {
		return Base64.encodeBytes(publicKey.getEncoded());
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param privateKey
	 * @return {@link String}
	 * @description: 转换私钥为字符串(Java)
	 */
	public static String privateKeyToString(RSAPrivateKey privateKey) {
		return Base64.encodeBytes(privateKey.getEncoded());
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param publicKey
	 * @return {@link PublicKey}
	 * @description: 转换字符串为公钥(Java)
	 */
	public static PublicKey getPublicKey(String publicKey) {
		try {
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
			KeyFactory factory;
			factory = KeyFactory.getInstance(KEY_ALGORITHM);
			return factory.generatePublic(x509EncodedKeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param privateKey
	 * @return {@link PrivateKey}
	 * @description: 转换字符串为私钥(Java)
	 */
	public static PrivateKey getPrivateKey(String privateKey) {
		try {
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			return factory.generatePrivate(pkcs8EncodedKeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @return {@link RSAKey}
	 * @description: 初始化密钥
	 */
	public static RSAKey generateKeyBytes() {
		KeyPairGenerator keyPairGen;
		try {
			keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(512);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			return new RSAKey(publicKey, privateKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param key
	 * @param plainText
	 * @return {@link String}
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IOException
	 * @description: 加密字符串
	 */
	public static String RSAEncode(PublicKey key, String plainText) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		byte[] b = plainText.getBytes("UTF-8");
		int inputLen = b.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(b, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(b, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return Base64.encodeBytes(decryptedData);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param key
	 * @param encodedText
	 * @return {@link String}
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IOException
	 * @description: 解密字符串
	 */
	public static String RSADecode(PrivateKey key, String encodedText) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {

		byte[] b = Base64.decode(encodedText);
		int inputLen = b.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(b, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(b, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, "UTF-8");
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param privateKey
	 * @return {@link String}
	 * @description: 转换私钥为.NET格式
	 */
	public static String getRSAPrivateKeyAsNetFormat(String privateKey) {
		try {
			StringBuffer buff = new StringBuffer(1024);

			PKCS8EncodedKeySpec pvkKeySpec = new PKCS8EncodedKeySpec(getPrivateKey(privateKey).getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateCrtKey pvkKey = (RSAPrivateCrtKey) keyFactory.generatePrivate(pvkKeySpec);

			buff.append("<RSAKeyValue>");
			buff.append("<Modulus>" + Base64.encodeBytes(removeMSZero(pvkKey.getModulus().toByteArray())) + "</Modulus>");
			buff.append("<Exponent>" + Base64.encodeBytes(removeMSZero(pvkKey.getPublicExponent().toByteArray())) + "</Exponent>");
			buff.append("<P>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrimeP().toByteArray())) + "</P>");
			buff.append("<Q>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrimeQ().toByteArray())) + "</Q>");
			buff.append("<DP>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrimeExponentP().toByteArray())) + "</DP>");
			buff.append("<DQ>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrimeExponentQ().toByteArray())) + "</DQ>");
			buff.append("<InverseQ>" + Base64.encodeBytes(removeMSZero(pvkKey.getCrtCoefficient().toByteArray())) + "</InverseQ>");
			buff.append("<D>" + Base64.encodeBytes(removeMSZero(pvkKey.getPrivateExponent().toByteArray())) + "</D>");
			buff.append("</RSAKeyValue>");
			return buff.toString();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param privateKey
	 * @return {@link String}
	 * @description: 转换公钥为.NET格式
	 */
	public static String getRSAPublicKeyAsNetFormat(String publicKey) {
		try {
			StringBuffer buff = new StringBuffer(1024);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKey pukKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(getPublicKey(publicKey).getEncoded()));
			buff.append("<RSAKeyValue>");
			buff.append("<Modulus>" + Base64.encodeBytes(removeMSZero(pukKey.getModulus().toByteArray())) + "</Modulus>");
			buff.append("<Exponent>" + Base64.encodeBytes(removeMSZero(pukKey.getPublicExponent().toByteArray())) + "</Exponent>");
			buff.append("</RSAKeyValue>");
			return buff.toString();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	private static byte[] removeMSZero(byte[] data) {
		byte[] temp;
		int len = data.length;
		if (data[0] == 0) {
			temp = new byte[data.length - 1];
			System.arraycopy(data, 1, temp, 0, len - 1);
		} else
			temp = data;
		return temp;
	}
}
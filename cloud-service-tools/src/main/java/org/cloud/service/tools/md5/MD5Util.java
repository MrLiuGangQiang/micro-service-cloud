package org.cloud.service.tools.md5;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: MD5工具类
 */
public class MD5Util {
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 屏蔽默认构造器
	 */
	private MD5Util() {
	}

	/**
	 * Copyright © 2018 Fist Team. All rights reserved.
	 *
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 内部静态类
	 */
	private static class MD5UtilInstance {
		private static final MD5Util INSTANCE = new MD5Util();
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @return {@link MD5Util}
	 * @description: 内部静态方法
	 */
	public static MD5Util getInstance() {
		return MD5UtilInstance.INSTANCE;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param text
	 * @return {@link String}
	 * @throws NoSuchAlgorithmException
	 * @description: 普通MD5加密
	 */
	public  String MD5(String text) throws NoSuchAlgorithmException {
		MessageDigest md5 = null;
		md5 = MessageDigest.getInstance("MD5");
		char[] charArray = text.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param text
	 * @return {@link String}
	 * @description: 随机加盐MD5加密
	 */
	public  String saltMD5(String text) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();
		text = md5Hex(text + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = text.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = text.charAt(i / 3 * 2 + 1);
		}
		return new String(cs);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param text
	 * @param md5
	 * @return {@link Boolean}
	 * @description: 比较原文和随机加盐MD5字符串
	 */
	public  boolean verify(String text, String md5) {
		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		return md5Hex(text + salt).equals(new String(cs1));
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param src
	 * @return {@link String}
	 * @description: 获取十六进制的MD5摘要
	 */
	private  String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param is 文件流
	 * @return {@link String}
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @description: 获取文件MD5编码
	 */
	public  String getFileMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
		StringBuffer md5 = new StringBuffer();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		while ((nread = is.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}
		byte[] mdbytes = md.digest();
		for (int i = 0; i < mdbytes.length; i++) {
			md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return md5.toString();
	}
}

package org.cloud.service.tools.code;

import java.util.Random;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: 随机验证码
 */
public class ValidCodeUtil {

	/**
	 * @type: {@link char[]}
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 数字数组
	 */
	private char[] numbers = "0123456789".toCharArray();

	/**
	 * @type: {@link char[]}
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 混合型数组 去掉了混淆的字符和数字
	 */
	private char[] words = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

	/**
	 * @type: {@link int}
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 最小长度
	 */
	private int MIN_LEN = 4;

	/**
	 * @type: {@link int}
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 最大长度
	 */
	private int MAX_LEN = 8;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 屏蔽默认构造器
	 */
	private ValidCodeUtil() {
	}

	/**
	 * Copyright © 2018 Fist Team. All rights reserved.
	 *
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @description: 内部静态类
	 */
	private static class ValidCodeUtilInstance {
		private static final ValidCodeUtil INSTANCE = new ValidCodeUtil();
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @return {@link ValidCodeUtil}
	 * @description: 内部静态方法
	 */
	public static ValidCodeUtil getInstance() {
		return ValidCodeUtilInstance.INSTANCE;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param len 长度
	 * @return {@link String}
	 * @description: 获取纯数字的验证码
	 */
	public String generateNumber(int len) {
		len = limitLen(len);
		Random random = new Random();
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = numbers[random.nextInt(numbers.length)];
		}
		return new String(cs);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月11日
	 * @param len 长度
	 * @return {@link String}
	 * @description: 获取混合型验证码
	 */
	public String generateCode(int len) {
		len = limitLen(len);
		Random random = new Random();
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = words[random.nextInt(words.length)];
		}
		return new String(cs);
	}

	private int limitLen(int len) {
		if (len < MIN_LEN) {
			return MIN_LEN;
		} else if (len > MAX_LEN) {
			return MAX_LEN;
		} else {
			return len;
		}
	}
}

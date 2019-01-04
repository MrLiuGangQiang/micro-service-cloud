package org.mongodb.file.service.intercept;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.cloud.service.tools.rsa.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月12日
 * @description: 权限过滤器
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月3日
	 * @description: 签名标识
	 */
	private static final String SIGN = "sign";
	@Value("${file.private.key}")
	private String key;
	@Value("${file.account}")
	private String account;
	@Value("${file.password}")
	private String password;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws JsonProcessingException, IOException {
		/* 构造就送处理对象 */
		ObjectMapper om = new ObjectMapper();
		/* 设置响应头字符编码为UTF-8 */
		response.setCharacterEncoding("UTF-8");
		/* 设置响应头为Json格式 */
		response.setContentType("application/json");
		/* 获取签名 */
		if (handler instanceof HandlerMethod) {
			String sign = request.getParameter(SIGN);
			if (sign == null || sign == "") {
				response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.BAD_REQUEST)));
				return false;
			}
			String plaintext = null;
			try {
				/* 解密签名 */
				plaintext = RSAUtil.RSADecode(RSAUtil.getPrivateKey(key), sign);
			} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | IOException e) {
				/* 解密异常提示无权限操作并打印日志 */
				if (logger.isErrorEnabled()) {
					logger.error("decrypt fail key<{}>", sign);
				}
				response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.UNAUTHORIZED)));
				return false;
			}
			if (plaintext != null && !"".equals(plaintext)) {
				/* json转map */
				@SuppressWarnings("unchecked")
				Map<String, Object> infoMap = om.readValue(plaintext, HashMap.class);
				/* 获取账号密码 并判断 */
				if (account.equals(infoMap.get("account")) && password.equals(infoMap.get("password"))) {
					/* 账号密码正确 判断令牌是否超过有效期 */
					Object createDateObj = infoMap.get("createDate");
					Object acquisitiveObj = infoMap.get("acquisitive");
					if (createDateObj != null && acquisitiveObj != null) {
						long createDate = Long.parseLong(createDateObj.toString());
						int acquisitive = Integer.parseInt(acquisitiveObj.toString());
						long time = System.currentTimeMillis();
						if (time <= createDate + acquisitive * 60 * 1000) {
							return true;
						} else {
							if (logger.isWarnEnabled()) {
								logger.warn("decrypt success but sign expiration info<{}>", infoMap);
							}
							response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.FORBIDDEN)));
							return false;
						}
					} else {
						if (logger.isWarnEnabled()) {
							logger.warn("decrypt success but incorrect information info<{}>", infoMap);
						}
						response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.FORBIDDEN)));
						return false;
					}
				} else {
					if (logger.isWarnEnabled()) {
						logger.warn("decrypt success but insufficient authority info<{}>", infoMap);
					}
					response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.FORBIDDEN)));
					return false;
				}
			}
			response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.FAIL)));
			return false;
		} else {
			response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.UNIMPLEMENTED)));
			return false;
		}
	}
}
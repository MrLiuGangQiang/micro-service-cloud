package org.module.web.user.intercept;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloud.service.core.auth.RequiresAuthentication;
import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.cloud.service.core.session.RedisSession;
import org.module.web.user.feign.iface.IUserSecurityService;
import org.module.web.user.global.BaseGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 权限拦截器
 */
public class UserSecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(UserSecurityInterceptor.class);
	@Resource
	private IUserSecurityService userSecurityService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			/* 构造就送处理对象 */
			ObjectMapper om = new ObjectMapper();
			/* 获取类全限定名 */
			String className = ((HandlerMethod) handler).getBeanType().getName();
			/* 获取方法名 */
			String methodName = ((HandlerMethod) handler).getMethod().getName();
			/* 设置响应头字符编码为UTF-8 */
			response.setCharacterEncoding("UTF-8");
			/* 设置响应头为Json格式 */
			response.setContentType("application/json");
			/* 获取token */
			String token = request.getHeader(BaseGlobal.TOKEN_FLAG);
			/* 获取机构ID */
			String oid = request.getHeader(BaseGlobal.ORGANIZATION_ID);
			Object uid = null;
			/* 获取方法 */
			HandlerMethod method = (HandlerMethod) handler;
			/* 获取权限注解 */
			RequiresAuthentication authentication = method.getMethodAnnotation(RequiresAuthentication.class);
			/* 如果权限注解为空不允许访问 */
			if (authentication == null) {
				if (logger.isWarnEnabled()) {
					logger.warn("the target method [{}#{}] is not defined", className, methodName);
				}
				response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.UNIMPLEMENTED)));
				return false;
			}
			/* 权限注解不为空 则进行鉴权判断 */
			if (authentication.ignore()) {
				/* 如果设置忽略校验直接放行 */
				return true;
			} else if (authentication.authc()) {
				/* 判断是否进行登录鉴权 */
				if (token == null) {
					/* token为空则返回参数有误状态 */
					response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.BAD_REQUEST)));
					return false;
				}
				JsonApi<RedisSession<Map<String, Object>>> sessionApi = userSecurityService.getLoginInfo(token);
				if (sessionApi.compare(ApiCodeEnum.OK)) {
					/* 登录信息不为空证明已登录，则放行 */
					RedisSession<?> session = sessionApi.getData();
					if (session != null) {
						return true;
					}
				}
				/* 未登录打印日志 */
				if (logger.isInfoEnabled()) {
					logger.info("the access target method [{}#{}] is not logged in", className, methodName);
				}
				response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.UNAUTHORIZED)));
				return false;
			} else {
				/* 先获取用户id */
				if (token == null) {
					/* token为空则返回参数有误状态 */
					response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.BAD_REQUEST)));
					return false;
				}
				JsonApi<RedisSession<Map<String, Object>>> sessionApi = userSecurityService.getLoginInfo(token);
				if (!sessionApi.compare(ApiCodeEnum.OK)) {
					response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.UNAUTHORIZED)));
					return false;
				}
				uid = sessionApi.getData().getData().get("id");
				/* 这里进行具体的业务权限判断 */
				switch (authentication.level()) {
				/* 角色判断 */
				case ROLE:
					JsonApi<Boolean> roleApi = userSecurityService.authByRole(uid, oid, authentication.value());
					if (roleApi.compare(ApiCodeEnum.OK)) {
						if (roleApi.getData()) {
							return true;
						}
					}
					response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.UNAUTHORIZED)));
					return false;
				case PERMISSION:
					JsonApi<Boolean> permissionApi = userSecurityService.authByPermission(uid, oid,
							authentication.value());
					if (permissionApi.compare(ApiCodeEnum.OK)) {
						if (permissionApi.getData()) {
							return true;
						}
					}
					response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.UNAUTHORIZED)));
					return false;
				case OPERATION:
					JsonApi<Boolean> operationApi = userSecurityService.authByOperation(uid, oid,
							authentication.value());
					if (operationApi.compare(ApiCodeEnum.OK)) {
						if (operationApi.getData()) {
							return true;
						}
					}
					response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.UNAUTHORIZED)));
					return false;
				}
			}
			if (logger.isInfoEnabled()) {
				logger.info("access target method [{}#{}] has no permissions", className, methodName);
			}
			response.getWriter().write(om.writeValueAsString(new JsonApi<>(ApiCodeEnum.FORBIDDEN)));
			return false;
		}
		return true;
	}
}
### 项目说明
该项目是一个典型的由Spring Cloud管理的微服务项目，主要包括如下模块
```
micro-service-cloud─────────────────顶层项目
├──cloud-service-core───────────────基础核心模块
├──cloud-service-tools──────────────全局通用工具类
├──cloud-service-reids──────────────Redis二次封装
├──cloud-eureka-server──────────────服务注册中心[8761]
├──cloud-turbine-server─────────────断路器聚合监控[8769]
├──cloud-zipkin-server──────────────链路追踪监控[9411]
├──cloud-zuul-server────────────────第一代服务网关(Zuul)[8080]
├──cloud-gateway-server─────────────第二代服务网关(Gateway)[8080]
├──cloud-modules-app────────────────App微服务模块
├───────modules-app-user────────────App用户服务模块[努力更新中]
├───────modules-app-doctor──────────App医生服务模块[努力更新中]
├──cloud-modules-service────────────微服务通用服务模块
├───────mongodb-file-service────────Mongodb文件服务模块[11010]
├───────redis-delay-service─────────延迟消费服务模块[11020]
├──cloud-modules-web────────────────Web微服务模块
├───────modules-web-security────────Web医生服务模块[12010]
├───────modules-web-user────────────Web用户服务模块[12020]
├──cloud-modules-wechat─────────────Wechat微服务模块
├───────modules-wechat-user─────────Wechat用户服务模块[努力更新中]
└───────modules-wechat-doctor───────Wechat医生服务模块[努力更新中]
 ```
  
* 修改日志

|修改日志|修改人|修改日期|版本计划|
|:----:|:----|:----:|:---|
|[V1.0](https://github.com/MrLiuGangQiang/micro-service-cloud/blob/master/README.md)|刘岗强|2019-01-07 |项目初始化|
|[V1.1](https://github.com/MrLiuGangQiang/micro-service-cloud/blob/master/README.md)|刘岗强|待定|新增自动问答|

### 项目介绍
1. 基于[Spring Cloud Finchley SR2](https://cloud.spring.io/spring-cloud-static/Finchley.SR2/) [Spring Boot 2.0.7](https://docs.spring.io/spring-boot/docs/2.0.7.RELEASE/reference/htmlsingle/)的最新版本。

2. 核心基础项目内实现类自定义的权限注解，配合RBAC权限模型+拦截器即可实现权限的控制，具体的参考项目中的实现。同时也封装了一些顶层类和结果集等。

3. 注册中心实现高可用配置，详情见eureka的one、two、three三个配置文件，摘要如下。<br>
------------------------------------------***配置节点一***----------------------------------------------
```yml
server:
  port: 8761
spring:
  application:
    name: cloud-eureka-server
eureka:
  instance:
    hostname: cloud.server.one
    prefer-ip-address: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}:${spring.application.name}
  client:
    healthcheck:
      enabled: true
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://cloud.server.two:8762/eureka/,http://cloud.server.three:8763/eureka/
```
------------------------------------------***配置节点二***----------------------------------------------
```yml      
server:
  port: 8762
spring:
  application:
    name: cloud-eureka-server
eureka:
  instance:
    hostname: cloud.server.two
    prefer-ip-address: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}:${spring.application.name}
  client:
    healthcheck:
      enabled: true
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://cloud.server.one:8761/eureka/,http://cloud.server.three:8763/eureka/
```
------------------------------------------***配置节点三***----------------------------------------------
```yml         
server:
  port: 8763
spring:
  application:
    name: cloud-eureka-server
eureka:
  instance:
    hostname: cloud.server.three
    prefer-ip-address: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}:${spring.application.name}
  client:
    healthcheck:
      enabled: true
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://cloud.server.two:8762/eureka/,http://cloud.server.one:8761/eureka/
```

4. 实现第一代网关(Zuul)和第二代网关(Gateway)，推荐使用第二代网关，原因不在赘述。同时两代网关都实现了全局异常捕获、全局fallback、熔断器超时配置、Ribbon负载策略配置等。摘要如下
```java
//全局异常捕获
@ExceptionHandler(Exception.class)
public JsonApi<?> defaultErrorHandler(Exception e) {
	if (logger.isErrorEnabled()) {
		logger.error("system appear error msg:{}", e.getMessage());
	}
	return new JsonApi<>(ApiCodeEnum.ERROR).setMsg(e.getMessage());
}
```
```java
/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月12日
 * @description: 全局熔断器配置(Zuul配置)
 */
@Component
public class ServerFallback implements FallbackProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider#
	 * getRoute()
	 */
	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				ObjectMapper mapper = new ObjectMapper();
				return new ByteArrayInputStream(mapper.writeValueAsString(new JsonApi<>(ApiCodeEnum.TIMEOUT)).getBytes("UTF-8"));
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}

			@Override
			public void close() {
			}
		};
	}
}
```
```java
/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月20日
 * @description: 全局熔断器配置(Gateway版本)
 */
@RestController
public class FallbackController {
	private static final Logger log = LoggerFactory.getLogger(FallbackController.class);

	@RequestMapping("/fallback/{lb}")
	public JsonApi<?> fallback(@PathVariable("lb") String lb) {
		log.error(Prompt.bundle("fallback.timeout", lb));
		return new JsonApi<>(ApiCodeEnum.TIMEOUT).setMsg(Prompt.bundle("fallback.timeout", lb));
	}
}
```
```yml
## Zuul中的超时配置，其中有个知识点就是hystrix的超时时间要大于ribbon的超时时间，
## 而ribbon的超时时间不是简单的ReadTimeout+ConnectTimeout那么简单它的规则简单用公式来说就是
## ribbonTimeout = (ReadTimeout + ConnectTimeout) * (maxAutoRetries + 1) * (maxAutoRetriesNextServer + 1)
## 源码请参考AbstractRibbonCommand中的getRibbonTimeout方法 其中也有hystrix的超时的逻辑
## 如果hystrix的超时时间小于公式计算的时间会导致ribbon在熔断器超时后还会继续重试
hystrix:
  command:
    default:
      circuitBreaker:
        requestVolumeThreshold: 10
      execution:
          thread:
            timeoutInMilliseconds: 30000
ribbon:
  ReadTimeout: 10000
  ConnectTimeout: 5000
  MaxAutoRetries: 0
  MaxAutoRetriesNextServer: 1
zuul:
  host:
    socket-timeout-millis: 10000
    connect-timeout-millis: 5000
```
```yml
## Gateway中的超时配置同上
hystrix:
  command:
    default:
      circuitBreaker:
        requestVolumeThreshold: 10
      execution:
          thread:
            timeoutInMilliseconds: 30000
ribbon:
  ReadTimeout: 10000
  ConnectTimeout: 5000
  MaxAutoRetries: 0
  MaxAutoRetriesNextServer: 1
```
```yml
## 负载均衡配置都是由ribbon提供，语法如下 策略可以是系统默认也可以自己实现IRule,默认规则参考IRule及其实现类
<客户端>:
  ribbon:
    NFLoadBalancerRuleClassName: <策略全限定路径>
```

5. 框架中包含了熔断器聚合监控、链路追踪监控，这里比较常规就不再赘述，唯一遇到的问题就是链路追踪时日志包重复引用的错误如下处理即可
```xml
<dependency>
	<groupId>io.zipkin.java</groupId>
	<artifactId>zipkin-server</artifactId>
	<version>${zipkin.version}</version>
	<!-- 剔除日志包 避免出现重复引用 -->
	<exclusions>
		<exclusion>
			<groupId>org.apache.logging.log4j</groupId>
			<artifactId>log4j-slf4j-impl</artifactId>
		</exclusion>
	</exclusions>
</dependency>
```
6. Redis二次封装的这个项目主要实现了自动延期的功能，可以在配置的时候设置某些缓存是否需要自动延期<默认为ture>,自动延期的将会在获取的时候重置
过期时间来达到自动延期功能。然后就是添加了一些方法来快捷操作登录的相关信息
```java
@Bean
public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
	/* redis序列化设置 */
	Jackson2JsonRedisSerializer<?> jsonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
	ObjectMapper om = new ObjectMapper();
	om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	jsonSerializer.setObjectMapper(om);

	RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
	config = config.entryTtl(Duration.ofMinutes(30))/* 设置默认过期时间 默认30分钟 */
			.disableCachingNullValues()/* 不缓存空值 */
			.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))/* 序列化key */
			.serializeValuesWith(SerializationPair.fromSerializer(jsonSerializer));/* 设置序列化方式 */

	/* 设置一个初始化的缓存空间set集合 */
	Set<String> cacheNames = new HashSet<>();
	cacheNames.add(BaseGlobal.CACHE_WEB_USER);

	/* 对每个缓存空间应用不同的配置 */
	Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
	configMap.put(BaseGlobal.CACHE_WEB_USER, config.setDelay(true));//自动延期 默认为true 
	//configMap.put(BaseGlobal.CACHE_WEB_USER, config.setDelay(false));//不自动延期

	RedisCacheManager cacheManager = RedisCacheManager.builder(factory) /* 使用自定义的缓存配置初始化一个cacheManager */
			.initialCacheNames(cacheNames) /* 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置 */
			.withInitialCacheConfigurations(configMap).build();
	return cacheManager;
}
```

7. 框架中的MongoDB项目是我用来作为文件服务器的一个实现，很简单实现了上传下载删除和预览的几个接口，大家可以看源码，有朋友问到秒传怎么做
这里只是简单说一个关键词（MD5）原理自行理解吧



### 个人连接
相关的技术说明会写在如下三个地方：
* [简书](https://www.jianshu.com/u/3642563a4185)
* [CSDN](https://blog.csdn.net/u010175879)
* [微信号：MrLiuGangQiang]<br>
![](https://github.com/MrLiuGangQiang/img/blob/master/user/%E5%BE%AE%E4%BF%A1%E4%BA%8C%E7%BB%B4%E7%A0%81.jpg)

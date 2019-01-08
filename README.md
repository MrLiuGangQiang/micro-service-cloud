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
1. 基于Spring Cloud Finchley SR2 Spring Boot 2.0.7的最新版本。
```xml
<!-- Spring boot版本 -->
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.0.7.RELEASE</version>
</parent>
<!-- 项目基本信息配置 -->
<groupId>com.microservice.cloud</groupId>
<artifactId>micro-service-cloud</artifactId>
<version>20181207.Alpha</version>
<!-- 声明项目类型 -->
<packaging>pom</packaging>
<!-- 项目属性配置 -->
<properties>
	<!-- Java版本 -->
	<java.version>1.8</java.version>
	<!-- 构建编码 -->
	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	<!-- Spring Cloud版本 -->
	<spring-cloud.version>Finchley.SR2</spring-cloud.version>
</properties>
<!-- 依赖版本配置 -->
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>${spring-cloud.version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```
2. 注册中心实现高可用配置，详情见eureka的one、two、three三个配置文件。
```yml
##----------------------------------------------------one
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
##----------------------------------------------------two
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
##----------------------------------------------------three
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
3. 实现第一代网关(Zuul)和第二代网关(Gateway)，推荐使用第二代网关，原因不在赘述。
4. 两代网关都配置了熔断器、超时重试以及负载均衡策略的配置实现IRule可实现自定义的负载均衡策略。
5. 

### 个人连接
相关的技术说明会写在如下三个地方：
* [简书](https://www.jianshu.com/u/3642563a4185)
* [CSDN](https://blog.csdn.net/u010175879)
* [微信号：MrLiuGangQiang]<br>
![](https://github.com/MrLiuGangQiang/img/blob/master/user/%E5%BE%AE%E4%BF%A1%E4%BA%8C%E7%BB%B4%E7%A0%81.jpg)

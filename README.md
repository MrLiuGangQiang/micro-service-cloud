### 项目说明
该项目是一个典型的由Spring Cloud管理的微服务项目，主要包括如下模块

> micro-service-cloud ──────────────────顶层项目<br>
>> cloud-eureka-server ──────────────服务注册中心[8761]<br>
>> cloud-gateway-server ─────────────服务网关中心[8080]<br>
>> cloud-modules-app ────────────────App微服务模块<br>
>>> modules-app-user ────────────App用户服务模块[待创建]<br>
>>> modules-app-doctor ──────────App医生服务模块[待创建]<br>
d
>> cloud-modules-service ────────────微服务通用服务模块<br>
>>> mongodb-file-service ────────Mongodb文件服务模块[11010]<br>
>>> redis-delay-service ─────────延迟消费服务模块[11020]<br>
>> cloud-modules-web ────────────────Web微服务模块<br>
>>> modules-web-security ────────Web医生服务模块[12010]<br>
>>> modules-web-user ────────────Web用户服务模块[12020]<br>
>> cloud-modules-wechat ─────────────Wechat微服务模块<br>
>>> modules-wechat-user ─────────Wechat用户服务模块[待创建]<br>
>>> modules-wechat-doctor ───────Wechat医生服务模块[带创建]<br>
>> cloud-service-core ───────────────基础核心模块<br>
>> cloud-service-reids ──────────────Redis二次封装<br>
>> cloud-service-tools ──────────────全局通用工具类<br>
>> cloud-turbine-server ─────────────断路器聚合监控[8769]<br>
>> cloud-zipkin-server ──────────────链路追踪监控[9411]<br>
>>> cloud-zuul-server ────────────────服务网关中心[8080]<br>

|服务名 | 父级依赖 | 模块说明|
|:----|:----|:----:|
|cloud-eureka-server|micro-service-cloud|微服务注册中心[8761]|
|cloud-zuul-server|micro-service-cloud|微服务第一代网关[8080]|
|cloud-gateway-server|micro-service-cloud|微服务第二代网关[8080]|
|cloud-service-core|micro-service-cloud|微服务基础核心模块|
|cloud-service-reids|micro-service-cloud|Redis二次封装|
|cloud-service-tools|micro-service-cloud|微服务通用工具类|
|cloud-turbine-server|micro-service-cloud|熔断器聚合监控[8769]|
|cloud-zipkin-server|micro-service-cloud|链路追踪监控[9411]|
|cloud-modules-app|micro-service-cloud|App微服务顶层项目|
|modules-app-user|cloud-modules-app|App用户模块微服务[***努力更新中***]|
|modules-app-doctor|cloud-modules-app|App医生模块微服务[***努力更新中***]| 
|cloud-modules-service|micro-service-cloud|微服务公共服务顶层项目|
|mongodb-file-service|cloud-modules-service|MongoDB文件服务器微服务[11010]|
|redis-delay-service|cloud-modules-service|基于Redis键过期通知的延迟消费服务[11020]|
|cloud-modules-web|micro-service-cloud|Web微服务顶层项目|
|modules-web-security|cloud-modules-web|Web用户通用权限模块[12010]|
|modules-web-user|cloud-modules-web|Web用户服务模块[12020]|
|cloud-modules-wechat|micro-service-cloud|Wechat微服务顶层项目|
|modules-wechat-user|cloud-modules-wechat|Wechat用户模块微服务[***努力更新中***]| 
|modules-wechat-doctor|cloud-modules-wechat|Wechat医生模块微服务[***努力更新中***]|
* 修改日志

|修改日志|修改人|修改日期|版本计划|
|:----|:----|:----|:---|
|[V1.0](https://github.com/MrLiuGangQiang/micro-service-cloud/blob/master/README.md)|刘岗强|2019-01-07 |项目初始化|

### 个人连接
相关的技术说明会写在如下三个地方：
* [简书](https://www.jianshu.com/u/3642563a4185)
* [CSDN](https://blog.csdn.net/u010175879)
* [微信号：MrLiuGangQiang]
![](http://ovheeg7ro.bkt.clouddn.com/aLiangcode.jpg)

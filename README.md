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
|:----:|:----|:----|:---|
|[V1.0](https://github.com/MrLiuGangQiang/micro-service-cloud/blob/master/README.md)|刘岗强|2019-01-07 |项目初始化|

### 个人连接
相关的技术说明会写在如下三个地方：
* [简书](https://www.jianshu.com/u/3642563a4185)
* [CSDN](https://blog.csdn.net/u010175879)
* [微信号：MrLiuGangQiang]
![](https://github.com/MrLiuGangQiang/micro-service-cloud/blob/master/wechat.jpg)

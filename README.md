项目目录结构<更新中>
micro-service-cloud ──────────────────顶层项目
├── cloud-eureka-server ──────────────服务注册中心[8761]
├── cloud-gateway-server ─────────────服务网关中心(第二代)[8080]
├── cloud-modules-app ────────────────App微服务模块
├    ├── modules-app-user ────────────App用户服务模块[更新中]
├    ├── modules-app-doctor ──────────App医生服务模块[更新中]
├── cloud-modules-service ────────────微服务通用服务模块
├    ├── mongodb-file-service ────────Mongodb文件服务模块[11010]
├    ├── redis-delay-service ─────────延迟消费服务模块[11020]
├── cloud-modules-web ────────────────Web微服务模块
├    ├── modules-web-security ────────Web医生服务模块[12010]
├    ├── modules-web-user ────────────Web用户服务模块[12020]
├── cloud-modules-wechat ─────────────Wechat微服务模块
├    ├── modules-wechat-user ─────────Wechat用户服务模块[更新中]
├    ├── modules-wechat-doctor ───────Wechat医生服务模块[更新中]
├── cloud-service-core ───────────────基础核心模块
├── cloud-service-reids ──────────────Redis二次封装
├── cloud-service-tools ──────────────全局通用工具类 
├── cloud-turbine-server ─────────────断路器聚合监控[8769]
├── cloud-zipkin-server ──────────────链路追踪监控[9411]
└── cloud-zuul-server ────────────────服务网关中心(第一代)[8080]

#### 项目说明
该项目是一个典型的由springcloud管理的微服务项目，主要包括如下模块

|服务名 | 父级依赖 | 模块说明|
|----|----|----        |
|thoth|无|为微服务提供统一的pom管理，以及通用组件|  
|thoth-registry-server|无|注册中心,Eureka|  
|thoth-config-server| 无|微服务统一配置中心|  
|thoth-robot-ms|无|springcloud中的一个微服务,其中包含一个简单的增删改查demo|  
|thoth-ai-ms|无|机器人聊天微服务，内部目前只实现了图灵机器人的调用| 
|thoth-docs|无|相关文档，技术规范以及编码规范| 

* 修改日志  

|修改日志 | 修改人 | 修改日期|版本计划|
|----|----|----        |---|
|如下，v1.0|阿亮|20171202        |加入zuul功能|

  - [v1.0](https://github.com/liangliang1259/springcloud-thoth/blob/v1.0/thoth-docs/update-log/2017-12-02.md)

#### To Do List
* 1.thoth基础组件的管理，后面会对pom中jar的版本管理做细粒度的切分和管理
* 2.网关以及熔断器等项目的更新
* 3.配置中心以及注册中心的使用
* 4.微服务之间的RPC通信，会有FeignClient和RestTemplate以及Grpc的整合。
* 5.项目部署方案，安全，授权等通用模块的更新。
* 6、项目中使用到的技术，包括es，kafaka，mongo，redis等集群模式的搭建及使用文档。



### Most import
*  文档：  
这一点我想很多同学跟我一样深受其害，就像github一样，很多项目拿下来不能用，没有文档和环境的说明，然后需要花费大量的时间去看代码。  
希望看到的小伙伴多给我提issue，我会尽量回复并文档化和规范化。


#### End
相关的技术说明会写在如下三个地方：  
* [简书](http://www.jianshu.com/u/13c5fab7db82)
* [个人博客](http://sunliangliang.com/)
* [公众号：阿亮私语]  
 


![](http://ovheeg7ro.bkt.clouddn.com/aLiangcode.jpg)




hosts配置
127.0.0.1 cloud.server.master
127.0.0.1 cloud.server.one
127.0.0.1 cloud.server.two
127.0.0.1 cloud.server.three
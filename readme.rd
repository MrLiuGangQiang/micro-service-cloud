micro-service-cloud ──────────────────顶层项目
├── cloud-eureka-server ──────────────服务注册中心[8761]
├── cloud-gateway-server ─────────────服务网关中心[8080]
├── cloud-modules-app ────────────────App微服务模块
├    ├── modules-app-user ────────────App用户服务模块[待创建]
├    ├── modules-app-doctor ──────────App医生服务模块[待创建]
├── cloud-modules-service ────────────微服务通用服务模块
├    ├── mongodb-file-service ────────Mongodb文件服务模块[11010]
├    ├── redis-delay-service ─────────延迟消费服务模块[11020]
├── cloud-modules-web ────────────────Web微服务模块
├    ├── modules-web-security ────────Web医生服务模块[12010]
├    ├── modules-web-user ────────────Web用户服务模块[12020]
├── cloud-modules-wechat ─────────────Wechat微服务模块
├    ├── modules-wechat-user ─────────Wechat用户服务模块[待创建]
├    ├── modules-wechat-doctor ───────Wechat医生服务模块[带创建]
├── cloud-service-core ───────────────基础核心模块
├── cloud-service-reids ──────────────Redis二次封装
├── cloud-service-tools ──────────────全局通用工具类 
├── cloud-turbine-server ─────────────断路器聚合监控[8769]
├── cloud-zipkin-server ──────────────链路追踪监控[9411]
└── cloud-zuul-server ────────────────服务网关中心[8080]

hosts配置
127.0.0.1 cloud.server.master
127.0.0.1 cloud.server.one
127.0.0.1 cloud.server.two
127.0.0.1 cloud.server.three
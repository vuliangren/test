# WeLearn Cloud Server

基于 SpringCloud 构建的微服务开发平台, 包括现有的 污水处理设备管理平台 和 医疗设备管理平台 都使用该项目作为后端

## 添加如下内容到本地 hosts 中
```
127.0.0.1 config.cloud.welearn
```

## 项目模块运行顺序
1. welearn-config-server
2. welearn-discovery-server
3. welearn-authorization-server
4. welearn-route-server
5. welearn-common-server

正常情况下需要将上述几个模块按顺序启动后, 才能继续运行其它的模块
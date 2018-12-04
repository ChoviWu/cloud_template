# springcloud-template

#### 项目介绍
This is a springcloud-template demo project. technology as springcloud,feign ,eureka etc.
please not using shangye as this project. if you have this,please fork or start this project.
thanks  

#### 软件架构
软件架构说明
初步使用了分布式微服务框架springcloud ，本例子为一个demo练习，切勿用于商业性活动
本例子用架构以及版本
    jdk1.8x
    tomcat8.x
    eureka-server
    springboot2.x
    springcloud2.x Finchley.RELEASE
    tk.mapper1.x
    mybatis1.3.x
    spring5.x
    rabbitmq
    redis
    oauth 
    
#### 安装教程

1. 环境  IDEA / eclipse
2. git clone git@github.com:ChoviWu/cloud_template.git
3. import idea download repository jar resource
#### 使用说明
启动说明：
    先启动springcloud-provider，该模块是项目的注册中心（eureka注册中心）
    然后启动其他模块
        springcloud-order模块为订单模块
        springcloud-message为消息中间件模块，该模块暂时只支持rabbitmq
        springcloud-rest模块为开放出来的rest API接口，供客户端进行调用
        springcloud-zuul为网关，用于过滤、授权等功能
        springcloud-auth为用户管理模块
        springcloud-core核心模块，主要就是存放一些算法类的，共用的东西
        
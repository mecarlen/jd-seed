# jd-seed
seed project init
1、项目介绍
    seed(种子项目) seed用于新技术与新思想实践孵化，当前是基于spring-boot-1.5.18进行各项技术孵化
    
1.1、项目设计指导思想：DDD、CQRS、EDD、TDD、微服务、敏捷编程

1.2、项目实践涉及技术
corejava 1.8
spring-boot-1.5.18.RELEASE
springMVC、springData
git
mysql 一主一从 实现读写分离
redis/jimdb
rocketMq消息组件，版本3.2
elasticsearch 实现在大文本存储和业务需求的聚合查询，保障领域对象的纯净2.4.2(->6.2.2)
zookeeper
logback->log4j2

分库分表
分布式任务

1.3、工程具体配置
工程编码UTF-8
JDK版本1.8
1.4、业务规约
金额都采用长整型，单位为币种最小单位，如人民币：分，美元：美分
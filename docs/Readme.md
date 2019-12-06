# SpringBoot 教程

### 目录结构
1、查看依赖关系，在Project View 选中指定的module，然后按alt + command + U

2、多模块：[SpringBoot多模块项目实践（Multi-Module） - - SegmentFault 思否](https://segmentfault.com/a/1190000011367492)
xiaobai-web: 控制层
xiaobai-service: 业务层
xiaobai-repo: 数据层
xiaobai-entity: 实体模型层


### 学习教程
0、[社区 Spring Boot 从入门到进阶系列教程 | Spring For All](http://www.spring4all.com/article/246)  

### 常用依赖介绍
0、依赖的关系、范围、排除等操作：  
[Maven 依赖范围、依赖传递、排除依赖](https://blog.csdn.net/sinat_25926481/article/details/76924780)    
[Maven依赖排除 禁止依赖传递 取消依赖的方法](https://blog.csdn.net/iteye_17920/article/details/82580332)  
1、lombok：[十分钟搞懂Lombok使用与原理](https://juejin.im/post/5a6eceb8f265da3e467555fe)  
2、commons-lang3：[Commons.lang 工具 - 后端](https://juejin.im/entry/58abf87dac502e00697aab6d)
3、guava：[Spring Boot 揭秘与实战（二） 数据缓存篇 - Guava Cache](https://juejin.im/post/586e43a6128fe100583f672e)    


### 遇到问题
1、application.properties 配置中文值的时候，读取出来的属性值会出现乱码问题。但是 application.yml 不会出现乱码问题。原因是，Spring Boot 是以 iso-8859 的编码方式读取 application.properties 配置文件。  
2、如果定义一个键值对 user.name=xxx ,这里会读取不到对应写的属性值。为什么呢？Spring Boot 的默认 StandardEnvironment 首先将会加载 “systemEnvironment" 作为首个PropertySource. 而 source 即为System.getProperties().当 getProperty时,按照读取顺序,返回 “systemEnvironment" 的值.即 System.getProperty("user.name")
3、[springboot启动报错：Error starting ApplicationContext. To display the auto-configuration report re-run yo - 灰太狼 - CSDN博客](https://blog.csdn.net/weixin_39220472/article/details/81429241)  

### 参考链接
1、[Spring Boot 之 HelloWorld详解](https://www.bysocket.com/archives/1124/spring-boot-%E4%B9%8B-helloworld%E8%AF%A6%E8%A7%A3)  
2、[spring boot要如何学习？ - 程序园](http://www.voidcn.com/article/p-suhlymbo-bry.html)  
3、[Github 上 Star 最多的个人 Spring Boot 开源学习项目 - 掘金](https://juejin.im/post/5c7765e9e51d4571fe4e58f1)  


### 开源工程链接
1、[ityouknow/spring-boot-examples - Spring Boot 教程、技术栈示例代码，快速简单上手教程。](https://github.com/ityouknow/spring-boot-examples)  
2、[springboot-learning-example: spring boot 实践学习案例](https://gitee.com/jeff1993/springboot-learning-example)


### IDEA插件
1、[值得推荐的Idea十几大优秀插件](https://blog.csdn.net/win7system/article/details/83508313)
2、Simple Module Dependency Graph - 到处依赖图，用法暂时不清楚。自带方式：在Project View 选中指定的module，然后按alt + command + U
  
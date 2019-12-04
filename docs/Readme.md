# SpringBoot 教程

### 学习教程
0、[社区 Spring Boot 从入门到进阶系列教程 | Spring For All](http://www.spring4all.com/article/246)  


### 遇到问题
1、application.properties 配置中文值的时候，读取出来的属性值会出现乱码问题。但是 application.yml 不会出现乱码问题。原因是，Spring Boot 是以 iso-8859 的编码方式读取 application.properties 配置文件。  
2、如果定义一个键值对 user.name=xxx ,这里会读取不到对应写的属性值。为什么呢？Spring Boot 的默认 StandardEnvironment 首先将会加载 “systemEnvironment" 作为首个PropertySource. 而 source 即为System.getProperties().当 getProperty时,按照读取顺序,返回 “systemEnvironment" 的值.即 System.getProperty("user.name")  

### 参考链接

1、[Spring Boot 之 HelloWorld详解](https://www.bysocket.com/archives/1124/spring-boot-%E4%B9%8B-helloworld%E8%AF%A6%E8%A7%A3)  
2、[spring boot要如何学习？ - 程序园](http://www.voidcn.com/article/p-suhlymbo-bry.html)  
3、[Github 上 Star 最多的个人 Spring Boot 开源学习项目 - 掘金](https://juejin.im/post/5c7765e9e51d4571fe4e58f1)  


### 开源工程链接
1、[ityouknow/spring-boot-examples - Spring Boot 教程、技术栈示例代码，快速简单上手教程。](https://github.com/ityouknow/spring-boot-examples)  
2、[springboot-learning-example: spring boot 实践学习案例](https://gitee.com/jeff1993/springboot-learning-example)  
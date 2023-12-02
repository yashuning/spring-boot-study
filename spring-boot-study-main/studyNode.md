[toc]

## springboot整合redis

参考链接：
> [SpringBoot教程(十四) | SpringBoot集成Redis(全网最全)](https://juejin.cn/post/7076244567569203208)

### Redis 连接池
> 客户端连接 Redis 使用的是 TCP协议，直连的方式每次需要建立 TCP连接，而连接池的方式是可以预先初始化好客户端连接，所以每次只需要从 连接池借用即可，而借用和归还操作是在本地进行的，只有少量的并发同步开销，远远小于新建TCP连接的开销。另外，直连的方式无法限制 redis客户端对象的个数，在极端情况下可能会造成连接泄漏，而连接池的形式可以有效的保护和控制资源的使用。
————————————————
版权声明：本文为CSDN博主「zzhongcy」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/zzhongcy/article/details/102584028

<font color="red">在 springboot 1.5.x版本的默认的Redis客户端是 Jedis实现的，springboot 2.x版本中默认客户端是用 lettuce实现的。</font>

### redis 序列化
redis 的序列化也是我们在使用 RedisTemplate 的过程中需要注意的事情。

RedisTemplate 这个类的泛型是<String,Object>，也就是他是支持写入Object对象的，那么这个对象采取什么方式序列化存入内存中就是它的序列化方式。

redis 提供的序列化方式：
- GenericToStringSerializer: 可以将任何对象泛化为字符串并序列化
- Jackson2JsonRedisSerializer: 跟JacksonJsonRedisSerializer实际上是一样的
- JacksonJsonRedisSerializer: 序列化object对象为json字符串
- JdkSerializationRedisSerializer: 序列化java对象
- StringRedisSerializer: 简单的字符串序列化

## spring整合mybatis
### 配置MyBatis Generator 
> 参考文档：https://juejin.cn/post/6844903982582743048

## maven-compiler-plugin 与spring-boot-maven-plugin 区别
1. maven-compiler-plugin是jar包生成插件，提供了manifest的配置，生成jar包中一般存放的是.class文件已经resources目录下的东西，文件很小。
2. spring-boot-maven-plugin主要目标是spring-boot的启动、停止、运行和repackage，对于打包来说那就是repackage，也就是说它实现的打包功能是重新打包，原始jar包还是由maven-jar-plugin生成的。
3. 使用maven-compiler-plugin生成的jar不能直接通过java -jar运行，提示：没有主清单属性。
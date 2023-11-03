## 定时任务
> 参考:  
> https://zhuanlan.zhihu.com/p/79644891  
> https://juejin.cn/post/7071138492251635748
  
定时任务处理，将定时任务写入数据库，动态读取定时任务

## 代理
> 参考:  
> https://juejin.cn/post/6844903744954433544  
> https://blog.csdn.net/jiankunking/article/details/52143504  
> https://github.com/guobinhit/cg-blog/blob/master/articles/others/dynamic-proxy.md (源码讲解较多)

### 动态代理

为什么类可以动态的生成？

涉及到Java虚拟机的类加载机制了：Java虚拟机类加载过程主要分为五个阶段：加载、验证、准备、解析、初始化；

加载阶段有三件事：
1. 通过一个类的全限定名来获取定义此类的二进制字节流
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
3. 在内存中生成一个代表这个类的 java.lang.Class 对象，作为方法区这个类的各种数据访问入口

所以，动态代理就是想办法，根据接口或目标对象，计算出代理类的字节码，然后再加载到JVM中使用。

为了让生成的代理类与目标对象（真实主题角色）保持一致性，从现在开始将介绍以下两种最常见的方式：
- 通过实现接口的方式 -> JDK动态代理
  - 提供者：JDK
  - 使用JDK官方的Proxy类创建代理对象
  - 注意：代理的目标对象必须实现接口
- 通过继承类的方式 -> CGLIB动态代理
  - 提供者：第三方 CGLib
  - 使用CGLib的Enhancer类创建代理对象
  - 注意：如果报 asmxxxx 异常，需要导入 asm.jar包


#### JDK动态代理

主要涉及两个类：`java.lang.reflect.Proxy` 和 `java.lang.reflect.InvocationHandler`

1. `implements InvocationHandler`
2. `Proxy.newProxyInstance`

#### CGLIB动态代理

1. `implements MethodInterceptor`
2. `Enhancer.create`

底层原理：
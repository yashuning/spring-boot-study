## web返回信息组件

web接口三种返回方式：

 - 使用@ResponseIntercept注解，自动会包装返回信息，包含code等
 - 使用工具自定义包装：ResponseResultUtil
 - 出现异常，拦截器会自动包装错误信息，如果不想使用自动封装，可以设置注解@ResponseNotIntercept

> 参考：  
> https://blog.csdn.net/weixin_43811057/article/details/127638674  
> https://www.cnblogs.com/sw-code/p/13956522.html  
> https://gitee.com/wl8888/controller/blob/master/controller/src/main/java/com/wxl52d41/advice/CommonResponseResult.java  
> https://juejin.cn/post/6986800656950493214?searchId=2023092522172225DCAD3B9E332203213E


### @RestControllerAdvice

@RestControllerAdvice：增强Controller的注解，是一个组合注解，包含@ControllerAdvice和@ResponseBody。 实现ResponseBodyAdvice接口 需要重写supports,beforeBodyWrite方法  

作用一般是用于拦截Controller方法的返回值，统一处理返回值/响应体， 加解密，签名等

 - 全局异常处理
 - 全局数据预处理
 - 全局数据绑定
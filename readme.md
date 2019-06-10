# 集创俱乐部
该项目为毕业设计项目,也是学习小程序.  
为便于理解,后台没有使用框架,用servlet+jdbc.前端用小程序,ui框架用iview.
### 模块介绍:
Java后台 + 小程序.  
``` lua
takeaway
├── api
     ├── jcclub -- Java代码
├── miniprogram
     ├── jcclub -- 小程序代码
```

#### 开发环境
- Jdk 1.8
- Mysql 5.7

### 启动应用

1. 后端
   1. 新建数据库jcclub,执行sql目录下的建表语句
   2. 修改api/jcclub/webapps/WEB-INF/config/dbconfig.properties下数据库信息
   3. 使用IDE启动应用
2. 小程序
    1. 修改app.js中全局常量requestUri
    2. 使用微信开发者工具启动
    
### 说明

1. Java
    1. 使用ListenerLoadConfig这个类,监听服务启动,将数据库的配置信息加载到内存中
    2. 每次HTTP请求,会根据@WebServlet中的值进行寻址,匹配到之后,再根据ActionName的值将请求分发到指定的方法.
    3. actionName指定的方法再去调用Dao中的具体方法,操作数据库
    4. 将结果返回到前端

2. 小程序：
    1. 官方文档:https://developers.weixin.qq.com/miniprogram/dev/
    2. 登录授权:http://caibaojian.com/wx-getuserinfo.html
    3. 异步转同步:http://www.wxapp-union.com/article-4376-1.html
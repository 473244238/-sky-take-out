#  SKY-OUT

### **1. HTTP 请求方法**

HTTP 协议定义了多种请求方法，常见的有：

- **GET**：从服务器获取资源。
- **POST**：向服务器提交数据，通常用于创建或更新资源。
- **PUT**：更新服务器上的资源。
- **DELETE**：删除服务器上的资源。
- **PATCH**：部分更新资源。

## 1.总结

![image-20250314131251593](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314131251593.png)

![image-20250314131321899](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314131321899.png)





![image-20250314131342135](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314131342135.png)



![image-20250314131400437](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314131400437.png)



![image-20250314131418892](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314131418892.png)





![image-20250314131556790](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314131556790.png)



## 2.数据库

### 1.Druid总结

```java
datasource:
    druid:
      driver-class-name: ${sky.datasource.driver-class-name}
      url: jdbc:mysql://${sky.datasource.host}:${sky.datasource.port}/${sky.datasource.database}?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
      username: ${sky.datasource.username}
      password: ${sky.datasource.password}sky:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    host: localhost
    port: 3306
    database: sky_take_out
    username: root
    password: 123456
```

这段配置代码的作用是配置Spring Boot应用程序使用Druid连接池连接MySQL数据库。具体配置包括：

- 数据库驱动类名：`com.mysql.cj.jdbc.Driver`
- 数据库连接URL：`jdbc:mysql://localhost:3306/sky_take_out`
- 连接参数：时区、字符编码、SSL设置等
- 用户名：`root`
- 密码：`123456`

通过占位符（`${}`）的方式，配置值可以从外部配置文件（如`application.yml`或`application.properties`）中动态加载，便于灵活管理和维护。



### **Druid连接池是什么？**

Druid是阿里巴巴开源的一个高性能、功能丰富的数据库连接池。它不仅仅是一个连接池，还提供了强大的监控和统计功能，能够帮助开发者更好地管理和优化数据库连接。Druid连接池在性能和功能上优于其他常见的连接池（如HikariCP、C3P0、Tomcat JDBC等），尤其是在监控和诊断方面表现突出。

#### **Druid连接池的核心功能：**

1. **高性能**：Druid在连接池的实现上做了很多优化，性能接近HikariCP。
2. **监控和统计**：Druid内置了监控功能，可以实时查看连接池的状态、SQL执行情况、慢查询等。
3. **SQL防注入**：Druid提供了WallFilter，可以防止SQL注入攻击。
4. **扩展性强**：支持自定义Filter，可以扩展连接池的功能。
5. **稳定性高**：经过阿里巴巴大规模生产环境的验证，稳定性非常好。

###  `@EnableTransactionManagementd`

#### 作用：

- 该注解用于**启用Spring的注解驱动的事务管理功能**。
- 它是Spring框架提供的注解，通常用在配置类（`@Configuration`标注的类）上。

#### 详细说明：

- 在Spring中，事务管理可以通过**编程式事务管理**或**声明式事务管理**来实现。
- `@EnableTransactionManagement` 开启了基于注解的声明式事务管理，允许你使用 `@Transactional` 注解来管理事务。
- 启用该注解后，Spring会自动扫描带有 `@Transactional` 注解的方法或类，并在方法执行时自动管理事务（如开启事务、提交事务、回滚事务等）。

### **`@Slf4j`**

#### 作用：

- 该注解是**Lombok库**提供的注解，用于自动生成一个日志对象（`Logger`），简化日志记录的代码。
- 它会在编译时自动生成一个名为 `log` 的静态日志对象，可以直接使用 `log` 来记录日志。

#### 详细说明：

- SLF4J（Simple Logging Facade for Java）是一个日志门面框架，提供了统一的日志接口，可以与多种日志实现（如Logback、Log4j等）集成

### **`@Data`**

#### **来源**：

- 该注解是 **Lombok** 提供的注解。

#### **作用**：

- `@Data` 是一个组合注解，它会自动生成以下内容：
  1. **Getter 方法**：为所有字段生成 `getter` 方法。
  2. **Setter 方法**：为所有非 `final` 字段生成 `setter` 方法。
  3. **`toString` 方法**：生成 `toString` 方法，输出对象的字符串表示。
  4. **`equals` 和 `hashCode` 方法**：生成 `equals` 和 `hashCode` 方法，用于对象比较和哈希计算。
  5. **`RequiredArgsConstructor`**：生成一个包含所有 `final` 字段和 `@NonNull` 字段的构造方法





## 3.前后端调节

![image-20250314140428218](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314140428218.png)

### JWT令牌的使用

1. 什么是JWT
JWT，即JSON Web Tokens，是一种基于JSON的开放标准（RFC 7519），用于在各方之间安全地传输信息。它经常被用于身份验证和授权的场景中。

2. JWT的组成
一个JWT令牌通常由三部分组成，以点号（.）分隔：

（1）头部（Header）

头部通常包含两部分信息：令牌的类型（即JWT）和所使用的签名算法（如HS256、RS256等）。
头部经过Base64Url编码形成JWT的第一部分。
（2）载荷（Payload）

载荷包含声明，声明是关于实体（通常是用户）和其他数据的声明。
这些声明分为三种：注册声明、公共声明和私有声明。
载荷同样经过Base64Url编码，形成JWT的第二部分。
（3）签名（Signature）

签名是为了确保令牌在传输过程中不被篡改。
签名的生成方式是将头部、载荷和密钥使用头部中指定的算法进行加密。
签名是JWT的第三部分，并不是经过Base64Url编码。
JWT令牌示例如下：



签名根据头部、载荷、密钥等生成。由于数字签名的存在，这些信息是可靠的。JWT令牌用于通信双方以JSON的格式安全传输数据信息。
![img](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\eb6b46d6e7f8498dacef616c356ee5b5.png)





### nignx反向代理

ginx (engine x) 是一个 高性能 的 HTTP 和 反向代理 web服务器，同时也提供了IMAP/POP3/SMTP服务。Nginx是由伊戈尔·赛索耶夫为俄罗斯访问量第二的Rambler.ru站点（俄文：Рамблер）开发的，第一个公开版本0.1.0发布于2004年10月4日。其将源代码以类BSD许可证的形式发布，因它的稳定性、丰富的功能集、简单的配置文件和低系统资源的消耗而闻名。2011年6月1日，nginx 1.0.4发布。支持 FastCGI、SSL、Virtual Host、URL Rewrite、Gzip 等功能。并且支持很多第三方的模块扩展。
Nginx是一款轻量级的 Web 服务器 / 反向代理服务器 及 电子邮件（IMAP/POP3）代理服务器，在BSD-like 协议下发行。其特点是占有内存少，并发能力强，事实上 nginx 的 并发能力 在同类型的网页服务器中表现较好，中国大陆使用nginx网站用户有：百度、京东、新浪、网易、腾讯、淘宝等。
Nginx 是高性能的 HTTP 和 反向代理的web服务器，处理高并发 能力是十分强大的，能经受高负载的考验,有报告表明能支持高达 50,000 个并发连接数。
Nginx支持 热部署，启动简单，可以做到 7*24 不间断运行。几个月都不需要重新启动。
    总而言之，Nginx是一个高性能、灵活和可扩展的Web服务器和代理服务器，适用于各种场景，包括静态文件服务、反向代理、负载均衡和动态内容处理等。
![image-20250314140833438](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314140833438.png)





![image-20250314140849864](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314140849864.png)





![image-20250314140908391](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314140908391.png)





![image-20250314140934367](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314140934367.png)





## 4.SWagger注解

![image-20250321212720777](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250321212720777.png)

使用方式：

![image-20250321212822571](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250321212822571.png)



![image-20250321213041990](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250321213041990.png)





![image-20250321213022893](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250321213022893.png)

![image-20250314141253384](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314141253384.png)









![image-20250314141423622](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314141423622.png)



![image-20250321212926358](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250321212926358.png)

## 5.新增员工

![image-20250314141511983](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314141511983.png)





![image-20250314141528015](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314141528015.png)



## 6.完善新增员工代码

### 代码存在的问题

![image-20250314150447679](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314150447679.png)



### 1.SQL异常处理

![image-20250314172536487](C:\Users\47324\AppData\Roaming\Typora\typora-user-images\image-20250314172536487.png)

这里由于我们数据设置用户名为独特，因此不能去重复的名字

这里我们要让前端明白我们是哪里出错了。

```java
 @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException sq){
        //Duplicate entry '4732442666' for key 'employee.idx_username
        String message=sq.getMessage();
        //如果有包含该重复账户名错误
        if(message.contains("Duplicate entry")){
            String[] spilt=message.split("");
            String username=spilt[2];
            String msg=username+ MessageConstant.ACCOUNT_EXIST;
            return  Result.success();
        }else{
            return  Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
```

![image-20250314173508848](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314173508848.png)



### 2.第二个问题：获取用户ID

![image-20250314195548233](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314195548233.png)



#### **ThreadLocal技术栈**

![image-20250314195747777](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314195747777.png)

## 7.员工分页

### 1.需求分析

![image-20250314200645151](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314200645151.png)

 

![image-20250314200738249](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314200738249.png)

**比较重要的是页码和每页记录数**

### 2.功能实现

**前端请求的代码页数**

![image-20250315094102893](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315094102893.png)

![image-20250314200900459](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314200900459.png)

 



**对于后端所有的分页查询，应该统一封装成PageResult对象**

![image-20250314201034376](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314201034376.png)



#### PageHelper的功能

- **分页功能**：PageHelper 提供了简单易用的分页功能，你只需要在查询方法前调用 `PageHelper.startPage(pageNum, pageSize)`，接下来的查询就会**自动进行分页**。
- **与Spring Boot集成**：`pagehelper-spring-boot-starter` 是专门为Spring Boot项目设计的，它简化了PageHelper的配置，通常只需要在 `application.properties` 或 `application.yml` 文件中进行简单的配置即可使用。

```xml
         <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper-spring-boot-starter</artifactId>
                <version>${pagehelper}</version>
            </dependency>
```



```xml
<mapper namespace="com.sky.mapper.EmployeeMapper">
    <select id="pageQuery" resultType="com.sky.entity.Employee">
      select * from employee
      <where>
          <if test="name != null and name != ''">
              and name like concat('%',#{name},'%')
          </if>
      </where>
      order by create_time desc 
    </select>
</mapper>
```

- **`namespace`**：指定了 Mapper 接口的全限定名，这里是 `com.sky.mapper.EmployeeMapper`，正确。
- **`id="pageQuery"`**：定义了查询方法的名称，与 Mapper 接口中的方法名对应，正确。
- **`resultType="com.sky.entity.Employee"`**：指定了返回结果的类型，这里是 `com.sky.entity.Employee`，正确。
- **`<where>` 标签**：用于动态生成 SQL 的 `WHERE` 子句，避免多余的 `AND` 或 `OR`，正确。
- **`<if>` 标签**：用于动态判断条件，如果 `name` 不为空，则拼接 `name like '%value%'` 的条件，正确。
- **`order by create_time desc`**：按照 `create_time` 字段降序排序，正确。

#### 在配置数据库xml文件，需要在application进行操作

![image-20250314205125897](C:\Users\47324\AppData\Roaming\Typora\typora-user-images\image-20250314205125897.png)

![image-20250314205139126](C:\Users\47324\AppData\Roaming\Typora\typora-user-images\image-20250314205139126.png)







**员工分页查询结果**

![image-20250314211153052](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314211153052.png)





### 3.代码完善

#### 如何对时间标准进行处理

![image-20250314211550125](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314211550125.png)



![image-20250314211829328](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314211829328.png)

 

![image-20250314213414099](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314213414099.png)





## 8.启用和禁用员工账号

### 1.需求分析

![image-20250314220428732](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314220428732.png)



**接口设计**

![image-20250314220508019](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250314220508019.png)



### 2.代码开发

```xml
    <update id="update" parameterType="com.sky.entity.Employee">
        update employee
        <set>
            <if test="name !=null">name = #{name},</if>
            <if test="username !=null">username = #{username},</if>
            <if test="password !=null">password = #{password},</if>
            <if test="phone !=null">phone = #{phone},</if>
            <if test="sex !=null">sex = #{sex},</if>
            <if test="idNumber !=null">id_Number = #{idNumber},</if>
            <if test="updateTime !=null">update _Time= #{updateTime},</if>
            <if test="updateUser !=null">update_User = #{updayeUser},</if>
            <if test="status !=null"> status= #{status},</if>
        </set>
                where id = #{id}
    </update>
```





## 9.编辑员工

### 1.需求分析

![image-20250315094027962](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315094027962.png)





![image-20250315094217580](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315094217580.png)



![image-20250315094323120](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315094323120.png)



## 10导入功能模块管理

![image-20250315110823219](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315110823219.png)

![image-20250315111228905](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315111228905.png)



![image-20250315111503610](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315111503610.png)





## 11.菜品管理

![image-20250315114159751](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315114159751.png)

### 1公共字段填充

![image-20250315114309653](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315114309653.png)



![image-20250315114500994](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315114500994.png)

![image-20250315114513837](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250315114513837.png)



#### **1. `@Target(ElementType.METHOD)`**

`@Target` 注解用于指定自定义注解可以应用的目标范围。`ElementType.METHOD` 表示该注解只能用于方法上。

#### **作用：**

- 限制注解的使用范围，确保注解只能用于方法上。
- 如果尝试将注解用于类、字段或其他地方，编译器会报错。

#### **常见的 `ElementType` 值：**

- `ElementType.TYPE`：用于类、接口、枚举等。
- `ElementType.FIELD`：用于字段（成员变量）。
- `ElementType.METHOD`：用于方法。
- `ElementType.PARAMETER`：用于方法参数。
- `ElementType.CONSTRUCTOR`：用于构造函数。
- `ElementType.LOCAL_VARIABLE`：用于局部变量。
- `ElementType.ANNOTATION_TYPE`：用于注解类型。
- `ElementType.PACKAGE`：用于包



#### **`@Retention(RetentionPolicy.RUNTIME)`**

`@Retention` 注解用于指定自定义注解的生命周期，即注解在什么阶段有效。`RetentionPolicy.RUNTIME` 表示该注解在运行时仍然可用。

#### **作用：**

- 控制注解的保留策略，决定注解在编译时、类加载时或运行时是否可用。
- `RetentionPolicy.RUNTIME` 表示注解在运行时可以通过反射机制读取。

#### **常见的 `RetentionPolicy` 值：**

- `RetentionPolicy.SOURCE`：注解仅在源代码中保留，编译时会被丢弃。例如 `@Override`。
- `RetentionPolicy.CLASS`：注解在编译时保留，但运行时不可用。这是默认的保留策略。
- `RetentionPolicy.RUNTIME`：注解在运行时保留，可以通过反射读取



```java
//前置通知代码
public class AutoFillAspect {
    /**
     * 插入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * 前置通知
     *
     */
    //在执行SQL方法之前，进行公共字段填充
     @Before("autoFillPointCut()")
     public void autoFill(JoinPoint joinPoint){
         log.info("开始进行公共字段的自动填充....");

         //获取当前被拦截的方法上的数据库操作类型
         MethodSignature signature=(MethodSignature)joinPoint.getSignature(); //方法签名对象
         AutoFill autoFill=signature.getMethod().getAnnotation(AutoFill.class); //获得方法上的注解对象
         OperationType operationType=autoFill.value(); //获得数据库的操作类型

         //获取当前被拦截的参数(约定：获取实体的第一个参数）
         Object[] args=joinPoint.getArgs();
         if(args==null || args.length==0){
             return;
         }
         Object entity=args[0];

         //准备赋值的数据
         LocalDateTime now=LocalDateTime.now();
         Long currentID= BaseContext.getCurrentId();

         // 根据当前不同的操作类型，为对应的属性通过反射来赋值
         if(operationType==OperationType.INSERT){
             //为4个字段赋值
             try{
                 Method setCreateTime=entity.getClass().getDeclaredMethod("setCreateTime",LocalDateTime.class);
                 Method setCreateUser=entity.getClass().getDeclaredMethod("setCreateUser",Long.class);
                 Method setUpdateTime=entity.getClass().getDeclaredMethod("setUpdateTime",LocalDateTime.class);
                 Method setUpdateUser=entity.getClass().getDeclaredMethod("updateUser",Long.class);

                 //通过发射来为对象属性赋值
                 setCreateTime.invoke(entity,now);
                 setCreateUser.invoke(entity,currentID);
                 setUpdateTime.invoke(entity,now);
                 setUpdateUser.invoke(entity,currentID);

             }catch(Exception e){
                 e.printStackTrace();
             }

         }else if(operationType==OperationType.UPDATE){
             try{
                 Method setUpdateTime=entity.getClass().getDeclaredMethod("setUpdateTime",LocalDateTime.class);
                 Method setUpdateUser=entity.getClass().getDeclaredMethod("updateUser",Long.class);

                 //通过发射来为对象属性赋值
                 setUpdateTime.invoke(entity,now);
                 setUpdateUser.invoke(entity,currentID);

             }catch(Exception e){
                 e.printStackTrace();
             }

         }
```

![image-20250322134923438](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322134923438.png)





### 2.新增菜品

1.**需求分析**

![image-20250322135149503](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322135149503.png)

**业务规则**

![image-20250322135239966](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322135239966.png)



**接口分析**

![image-20250322135314109](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322135314109.png)

![image-20250322135433216](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322135433216.png)

![image-20250322135454981](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322135454981.png)



![image-20250322135550167](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322135550167.png)

**口味和菜品的数据库关联关系**

![image-20250322135836295](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322135836295.png)



#### （1)开发文件上传接口

![image-20250322135928411](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322135928411.png)

```java

```

![image-20250322140632979](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322140632979.png)



![image-20250322140649507](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322140649507.png)

![image-20250322141529371](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250322141529371.png)



**建立工具类来获得远程阿里云服务**

```java
    /**
     * 文件上传
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        log.info("文件上传到:{}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}
```

#### (2)**新增菜品加口味**

注意两个数据库关联



### 3.菜品分页查询

![image-20250327110532595](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327110532595.png)

**业务规则：**

![image-20250327110606842](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327110606842.png)

**接口设计**

![image-20250327110658642](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327110658642.png)



**设计对应的DTO（前端传给后端的页面）**

![image-20250327110845098](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327110845098.png)



**设计对应的VO（后端传给前端的页面）**

![image-20250327110931955](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327110931955.png)



```sql
//插入SQL语句
    <select id="pageQuery" resultType="com.sky.vo.DishVO">
        select d.* , c.name as categoryName from dish d left outer join category c on d_id=c.id
    <where>
        <if test="name != null">
            and d.name like concat('%',#{name},'%')
        </if>
        <if test="categoryId != null">
            and d.category_id = #{categoryId}
        </if>
        <if test="status != null">
            and d.status = #{status}
        </if>
    


<select>：定义了一个查询语句
id="pageQuery"：这个查询的唯一标识符
resultType="com.sky.vo.DishVO"：查询结果将映射到DishVO类
从dish表(别名d)中查询所有字段(d.*)
通过left outer join关联category表(别名c)，关联条件是d.category_id = c.id
从category表中查询name字段，并命名为categoryName(这样映射到DishVO对象时会有对应的字段)
动态WHERE条件：
<where>标签会自动处理条件前的AND/OR，如果条件都不满足会去掉WHERE关键字
三个<if>条件判断：
如果传入的name参数不为null，则添加菜品名称模糊查询条件
如果传入的categoryId参数不为null，则添加按分类ID精确查询条件
如果传入的status参数不为null，则添加按状态精确查询条件
```



### 4.删除菜品

![image-20250327122728259](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327122728259.png)

![image-20250327122842738](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327122842738.png)



**三个数据库关联（dish表，dish_flavor表，setmeal_dish表）**

![image-20250327123009220](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327123009220.png)



### 5.修改菜品

![image-20250327165713151](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327165713151.png)



![image-20250327165739469](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250327165739469.png)





## 12.套餐管理

**完成的功能**：

完成套餐管理模块所有业务功能，包括：

- 新增套餐
- 套餐分页查询
- 删除套餐
- 修改套餐
- 起售停售套餐



### 1.新增套餐

![image-20250407164248706](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250407164248706.png)

![image-20250407164317514](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250407164317514.png)

**业务规则：**

套餐名称唯一

套餐必须属于某个分类

套餐必须包含菜品

名称，分类，价格，图片为必填项

添加菜品窗口需要根据分类类型来展示菜品

新增的套餐默认为停售状态

**接口设计**

1.根据类型查询分类

2.根据id查询菜品

3.图片上传

4.新增套餐

![image-20250407165405252](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250407165405252.png)

![image-20250407165417240](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250407165417240.png)

![image-20250407165658669](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250407165658669.png)



### 2.套餐分页查询

![image-20250408182124587](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250408182124587.png)

业务规则：

根据页码进行展示

每页可以展示10条数据

根据需要，按照所命名的字体进行查询（模糊查询）

![image-20250408194637543](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250408194637543.png)

### 3.删除套餐

![image-20250408202256635](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250408202256635.png)



### 4.修改套餐

![image-20250408203739386](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250408203739386.png)

**接口设计**

根据id查询套餐

根据类型查询分类

根据分类id查询菜品

图片上传

修改套餐



### 5.停售/起售套餐

![image-20250408211127984](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250408211127984.png)

业务规则：

可以对状态为起售套餐进行停售操作，可以对状态为停售的套餐进行起售操作

起售的套餐可以展示在用户端，停售的套餐不能展示在用户端

起售套餐时，如果套餐包含停售的菜品，则不能起售

![image-20250408211311430](C:\Users\47324\Desktop\JAVA_basic\Spring-DelieverySystem\图片\image-20250408211311430.png)
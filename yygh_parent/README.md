# yygh_parent
医院挂号系统
开发中遇到的问题
1.整合swagger时启动类添加的扫包一定要包含使用者服务com.atguigu.yygh.hosp和swageer服务com.atguigu.yygh.common所有使用服务启动类
   @ComponentScan(basePackages = {"com.atguigu.yygh"})
2.配置mybaitis-plus代码生成器
  需要引入三个依赖mybatis-plus-boot-starter mybatis-plus-generator 3.2.0  velocity-engine-core2.3
3.使用Ipage分页，当前页和页面大小错误需要编写mybatis_plus配置类注入bean PaginationInterceptor才能起到效果
4.vue前端封装json数据时。后端使用注解@requestBody接收不到。我的错误是因为api js 参数key写成了params应该写为data就好了。网上提过另外
一种说法是把对象转为json格式 Json.stringify(object)
5.application.yml配置文件不生效的问题。原因是targer文件夹的jar里面没有编译yml文件。所以在需要打包的服务添加可以把配置文件加载进去的打包配置
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.yml</include>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes> <include>**/*.yml</include>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
6.后端有多个服务，vue前端的dev.env.js文件的BASE_API指定一个端口。可以使用nginx代理实现或者使用网关实现
7.使用element-ui加载层级关系的表。element-ui版本太低只能加载子层的数据，需要升级element-ui版本在package.json将element-ui
  版本设置为2.12.0完了npm install重新安装依赖
8. liunx安装启动mongodb一定要指定ip为0.0.0.0 mongodb默认使用的是127.0.0.1只让本机连不能远程连接
8.下拉框二级联动的时候。二级下拉框选中不显示。因为层级关系太多没有更新过来，在二级下来框添加函数。函数里使用 this.$forceUpdate()强制更新一下就好了
  

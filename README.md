# yygh_parent
医院挂号系统
开发中遇到的问题
1.整合swagger时启动类添加的扫包一定要包含使用者服务com.atguigu.yygh.hosp和swageer服务com.atguigu.yygh.common所有使用服务启动类
   @ComponentScan(basePackages = {"com.atguigu.yygh"})
2.配置mybaitis-plus代码生成器
  需要引入三个依赖mybatis-plus-boot-starter mybatis-plus-generator 3.2.0  velocity-engine-core2.3
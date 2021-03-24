package com.atguigu.yygh.cmn;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangjie
 * @Title: ServiceCmnApplicationTest
 * @Description: 测试类
 * @company: 西安石文软件有限公司
 * @date 2021/3/2222:26
 */
@SpringBootTest
public class ServiceCmnApplicationTest {

    @Test
    public void  demo(){
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:\\ideaworkspace02\\yygh\\yygh_parent\\service\\service-cmn" + "/src/main/java");
        gc.setAuthor("wangjie");
        gc.setOpen(false); //生成后是否打开资源管理器（及生成后是否展开目录树）
        gc.setFileOverride(false); //重新生成时文件是否覆盖（生成代码改了之后，再此生成会覆盖已经修改的代码）
        //UserServie
        gc.setServiceName("%sService");    //去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略 （mybatis-puls的主键生成策略给逐渐类型是字符类型就是ID_WORKER_STR 数字类型就是ID_WORKER 这个是根据雪花算法生成的19位数据）
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式
        mpg.setGlobalConfig(gc);
        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.79.128:3306/yygh_cmn?characterEncoding=utf-8&useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1234");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("cmn"); //模块名（com.it包后面添加.eduservice）
        //包  com.atguigu.eduservice
        pc.setParent("com.atguigu.yygh");
        //包  com.atguigu.eduservice.controller
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);
        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("dict"); //需要操作的表名 如果是多个表需要使用英文","隔开
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略（表示如果数据库字段是user_name,那么实体就是userName属性）
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        mpg.setStrategy(strategy);
        // 6、执行
        mpg.execute();
    }
}

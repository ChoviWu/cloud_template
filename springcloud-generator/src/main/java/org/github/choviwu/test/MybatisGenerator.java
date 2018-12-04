package org.github..test;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author
 * @date 2018/8/30
 * Description :E:\springcloud-template
 */
public class MybatisGenerator {

    private static String packageName="";    //文件路径
    private static String prefix="";                     //table前缀
    private static File file = new File(packageName);
    private static String  url ;
    private static String  username ;
    private static String  password ;
    private static String  driver ;
    private static String  baseFoldPackage ;
    private static String  parentBuildPath ;
    private static String  tableNames ;
    private static String  authorName ;
    private static String  basePackage ;
    private static String  userController ;
    private static String  userEntity ;
    private static String  userMapper ;
    private static String  userApi ;
    private static String  userServiceImpl ;
    private static String  module1 ;
    private static String  module2 ;
    private static String  module3 ;
    private static String  module4 ;
    private static String  xmlMapper ;
    private static String  resourceFoldPackage ;
    public MybatisGenerator() throws IOException {
        Properties properties = new Properties();
        properties.load(MybatisGenerator.class.getClassLoader().getResourceAsStream("generator.properties"));
        properties.list(System.out);
        url =   properties.getProperty("db.url");
        username = properties.getProperty("db.username");
        password =  properties.getProperty("db.password");
        driver = properties.getProperty("db.driver");
        baseFoldPackage = properties.getProperty("gen.base_fold_package");
        resourceFoldPackage = properties.getProperty("gen.resource_fold_package");
        parentBuildPath = properties.getProperty("gen.parent_build_path");
        tableNames = properties.getProperty("gen.table_names");
        authorName = properties.getProperty("gen.author_name");
        basePackage = properties.getProperty("gen.base_package");
        userController = properties.getProperty("gen.user_controller");
        userEntity = properties.getProperty("gen.user_entity");
        userMapper = properties.getProperty("gen.user_mapper");
        userApi = properties.getProperty("gen.user_api");
        userServiceImpl = properties.getProperty("gen.user_service_impl");
        module1 = properties.getProperty("gen.module1");
        module2 = properties.getProperty("gen.module2");
        module3 = properties.getProperty("gen.module3");
        module4 = properties.getProperty("gen.module4");
        xmlMapper = properties.getProperty("gen.xml_mapper");
    }
    public static void main(String[] args) throws IOException {

        MybatisGenerator generator = new MybatisGenerator();
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(parentBuildPath + baseFoldPackage)//输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setOpen(false)//生成后打开文件夹
                        .setAuthor(authorName)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setMapperName("%sMapper")
                        .setXmlName("%sMapper")
                        .setServiceName("%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setControllerName("%sController")
        );
        mpg.setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL)// 数据库类型
                        .setTypeConvert(new MySqlTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public DbColumnType processTypeConvert(String fieldType) {
                                System.out.println("转换类型：" + fieldType);
                                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                                //    return DbColumnType.BOOLEAN;
                                // }
                                return super.processTypeConvert(fieldType);
                            }
                        })
                        .setDriverName(driver)
                        .setUsername(username)
                        .setPassword(password)
                        .setUrl(url)
        );

        String [] tables = tableNames.split(",");
        mpg.setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        .setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setInclude(tables) // 需要生成的表
                        .setRestControllerStyle(true)
                        //.setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                        // 自定义实体，公共字段
                        //.setSuperEntityColumns(new String[]{"test_id"})
                        .setTableFillList(tableFillList)
                // 自定义 mapper 父类
                // .setSuperMapperClass("com.baomidou.demo.TestMapper")
                // 自定义 service 父类
                // .setSuperServiceClass("com.baomidou.demo.TestService")
                // 自定义 service 实现类父类
                // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                // 自定义 controller 父类
//                        .setSuperControllerClass("com.tdx."+packageName+".controller.AbstractController")
                // 【实体】是否生成字段常量（默认 false）
                // public static final String ID = "test_id";
                // .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // public User setName(String name) {this.name = name; return this;}
                // .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                // .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
        );
        mpg.setPackageInfo(
                // 包配置
                new PackageConfig()
                        //.setModuleName("User")
                        .setParent(basePackage )// 自定义包路径
                        .setController("rest.controller")// 这里是控制器包名，默认 web
                        .setEntity("common.model")
                        .setMapper("auth.mapper")
                        .setService("api")
                        .setServiceImpl("auth.service")
                //.setXml("mapper")
        );

        FileOutConfig mapperConfig = getMapperConfig();
        FileOutConfig entityConfig = getEntityConfig();
        FileOutConfig serviceConfig = getServiceConfig();
        FileOutConfig serviceImplConfig = getServiceImplConfig();
        FileOutConfig contolConfig = getContolConfig();
        FileOutConfig daoConfig = getDaoConfig();
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        fileOutConfigs.add(mapperConfig);
        fileOutConfigs.add(entityConfig);
        fileOutConfigs.add(serviceConfig);
        fileOutConfigs.add(serviceImplConfig);
        fileOutConfigs.add(contolConfig);
        fileOutConfigs.add(daoConfig);
        mpg.setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() );
                        this.setMap(map);
                    }
                }.setFileOutConfigList(fileOutConfigs)
        ); mpg.setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                        .setController(null)
                        .setEntity(null)
                        .setMapper(null)
                        .setService(null)
                        .setServiceImpl(null)
        );

        // 执行生成
        mpg.execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

    public static FileOutConfig getMapperConfig() {
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/mapper.xml.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                String mapperPath =  xmlMapper+ tableInfo.getEntityName() + "Mapper.xml";
                System.out.println("===================MapperPath:" + mapperPath);
                return mapperPath;
            }

        };
        return fileOutConfig;
    }

    public static FileOutConfig getEntityConfig() {
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/entity.java.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityPath = userEntity + tableInfo.getEntityName() +".java";
                System.out.println("===================EntityPath:" + entityPath);
                return entityPath;
            }

        };
        return fileOutConfig;
    }

    public static FileOutConfig getServiceConfig() {
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/service.java.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                String servicePath =   userApi + tableInfo.getEntityName() + "Service.java";
                System.out.println("===================servicePathPath:" + servicePath);
                return servicePath;
            }

        };
        return fileOutConfig;
    }

    public static FileOutConfig getServiceImplConfig() {
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/serviceImpl.java.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                String implPath =  userServiceImpl + tableInfo.getEntityName() + "ServiceImpl.java";
                System.out.println("===================implPathPathPath:" + implPath);
                return implPath;
            }

        };
        return fileOutConfig;
    }

    public static FileOutConfig getContolConfig() {
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/controller.java.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                //"/springcloud-rest/src/main/java/org/github//rest/controller/"
                String controller =  userController + tableInfo.getEntityName() + "Controller.java";
                System.out.println("=================controllerPath : " + controller);
                return controller;
            }

        };
        return fileOutConfig;
    }

    public static FileOutConfig getDaoConfig() {
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/mapper.java.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                String dao =  userMapper + tableInfo.getEntityName() + "Mapper.java";
                System.out.println("=================daoPath : " + dao);
                return dao;
            }

        };
        return fileOutConfig;
    }
}

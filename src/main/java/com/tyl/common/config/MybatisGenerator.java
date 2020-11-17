package com.tyl.common.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * mybatis-plus 代码生成
 */
public class MybatisGenerator {


    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        Map<String, String> read = read();
        if (read == null) {
            throw new RuntimeException("获取数据库配置异常");
        }
        String moduleName = read.get("spring.application.name") == null ? "" : read.get("spring.application.name");
        mpg.setGlobalConfig(globalConfig());
        mpg.setCfg(cfg(moduleName));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setTemplate(new TemplateConfig()
                .setService(null)
                .setController(null)
                .setMapper(null)
                .setServiceImpl(null)
                .setXml(null));
        mpg.setDataSource(mysqlDataSourceConfig());
        mpg.setPackageInfo(packageConfig(moduleName, "com.tyl"));
        mpg.setStrategy(strategyConfig("jpa_user"));
        mpg.execute();
    }

    private static InjectionConfig cfg(String moduleName) {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        }.setFileOutConfigList(fileOutConfigs(moduleName));
    }

    private static List<FileOutConfig> fileOutConfigs(String moduleName) {
        String property = System.getProperty("user.dir");
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        fileOutConfigs.add(new FileOutConfig(
                "/templates/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = property + "/src/main/java/com/tyl/" + moduleName + "/" + "controller";
                return String.format((expand + File.separator + "%s" + StringPool.DOT_JAVA), tableInfo.getControllerName());
            }
        });
        fileOutConfigs.add(new FileOutConfig(
                "/templates/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = property + "/src/main/java/com/tyl/" + moduleName + "/service";
                return String.format((expand + File.separator + "%s" + StringPool.DOT_JAVA), tableInfo.getEntityName() + "Service");
            }
        });
        fileOutConfigs.add(new FileOutConfig(
                "/templates/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = property + "/src/main/java/com/tyl/" + moduleName + "/service/impl/";
                return String.format((expand + File.separator + "%s" + StringPool.DOT_JAVA), tableInfo.getServiceImplName());
            }
        });
        fileOutConfigs.add(new FileOutConfig(
                "/templates/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = property + "/src/main/java/com/tyl/" + moduleName + "/mapper/";
                return String.format((expand + File.separator + "%s" + StringPool.DOT_JAVA), tableInfo.getMapperName());
            }
        });
        fileOutConfigs.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return property + "/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        return fileOutConfigs;
    }

    /**
     * 全局配置
     */
    private static GlobalConfig globalConfig() {
        String property = System.getProperty("user.dir");
        return new GlobalConfig()
                .setOutputDir(property + "/src/main/java/")
                .setFileOverride(true)
                .setOpen(false)
                .setAuthor("tyl")
                .setSwagger2(true);
    }

    /**
     * 包配置
     *
     * @param moduleName 模块名称
     * @param parent     父路径
     */
    private static PackageConfig packageConfig(String moduleName, String parent) {
        return new PackageConfig()
                .setModuleName(moduleName)
                .setParent(parent);
    }


    /**
     * 策略配置
     */
    private static StrategyConfig strategyConfig(String tableName) {
        return new StrategyConfig()
                .setInclude(tableName) // 包含的表名
                .setEntityLombokModel(true)  // 实体相关配置
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true);
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig mysqlDataSourceConfig() {
        Map<String, String> read = read();
        if (read == null) {
            throw new RuntimeException("获取数据库配置异常");
        }
        // 数据源配置
        return new DataSourceConfig()
                .setUrl(read.get("spring.datasource.url"))
                // dsc.setSchemaName("public");
                .setDriverName(read.get("spring.datasource.driver-class-name"))
                .setUsername(read.get("spring.datasource.username"))
                .setPassword(read.get("spring.datasource.password"));
    }

    private static Map<String, String> read() {
        Map<String, String> map = new HashMap<>();
        Properties properties = new Properties();
        try {
            InputStream in = MybatisGenerator.class.getClassLoader().getResourceAsStream("application.properties");
            if (in == null) {
                throw new RuntimeException("获取数据库配置异常");
            }
            properties.load(in);
            properties.stringPropertyNames().forEach(string ->
                    map.put(string, properties.getProperty(string))
            );
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

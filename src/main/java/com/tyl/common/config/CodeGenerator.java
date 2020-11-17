package com.tyl.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.sql.Driver;
import java.util.*;

/**
 * mybatis-plus 代码生成
 */
public class CodeGenerator {

    public static void main(String[] args) {
        /**
         * MySQL 生成演示
         */
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
        String property = System.getProperty("user.dir");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(property + "/src/main/java")//输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setOpen(false)// 是否覆盖文件
                        .setActiveRecord(false)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setAuthor("taoyalei")
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL) // 数据库类型
                        .setTypeConvert(new MySqlTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                                System.out.println("转换类型：" + fieldType);
                                return super.processTypeConvert(config, fieldType);
                            }
                        })
                        .setDbQuery(new MySqlQuery() {
                            /**
                             * 重写父类预留查询自定义字段<br>
                             * 这里查询的 SQL 对应父类 tableFieldsSql 的查询字段，默认不能满足你的需求请重写它<br>
                             * 模板中调用：  table.fields 获取所有字段信息，
                             * 然后循环字段获取 field.customMap 从 MAP 中获取注入字段如下  NULL 或者 PRIVILEGES
                             */
                            @Override
                            public String[] fieldCustom() {
                                return new String[]{"NULL", "PRIVILEGES"};
                            }
                        })
                        .setDriverName(Driver.class.getName())
                        .setUsername("root")
                        .setPassword("Zzwl@2020")
                        .setUrl("jdbc:mysql://192.168.5.66:3306/test?useUnicode=true&allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8")
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        .setTablePrefix(new String[]{"jpa_"})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setTableFillList(tableFillList)
                        .setEntityTableFieldAnnotationEnable(true)
                        .setEntityBooleanColumnRemoveIsPrefix(true)
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        .setModuleName("user")
                        .setParent("com.tyl")// 自定义包路径
                        .setController("controller")// 这里是控制器包名，默认 web
        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(
                        "/templates/controller.java.ftl") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名 + pc.getModuleName()
                        String expand = property + "/src/main/java/com/tyl/" + "user" + "/" + "controller";
                        String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getControllerName());
                        return entityFile;
                    }
                }))
        );
        // 执行生成
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}
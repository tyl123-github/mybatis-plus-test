package com.tyl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.IKeyWordsHandler;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class MybatisPlusTestApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 获得数据库表的元数据
     */

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Map<String, Map<String, String>> computerMall = getDatabaseNameToTableNameAndColumnName();
        Set<String> tableNames = computerMall.keySet();
        System.out.println("一共有表：" + tableNames.size() + "张");
        for (String tableName : tableNames) {
            System.out.println("===========================\r\n");
            System.out.println("表名：" + tableName);
            // <字段名,类型>  HashMap 集合
            Map<String, String> columnNames = computerMall.get(tableName);
            Set<String> columns = columnNames.keySet();
            for (String column : columns) {
                System.out.println("字段名：" + column + " 类型：" + columnNames.get(column));
            }
            System.out.println("===========================\r\n");
        }
    }

    private static Map<String, Map<String, String>> getDatabaseNameToTableNameAndColumnName() throws SQLException, ClassNotFoundException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.5.66:3306/test";
        String username = "root";
        String password = "Zzwl@2020";
        Map<String, Map<String, String>> tableNameMap = new HashMap<>();
        //加载驱动
        Class.forName(driver);
        //获得数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        StringBuilder sql = new StringBuilder("show table status WHERE 1=1 ");
        List<String> tables = new ArrayList<>();
        tables.add("jpa_user");
        sql.append(" AND ").append("name").append(" IN (").append(tables.stream().map(tb -> "'" + tb + "'").collect(Collectors.joining(","))).append(")");
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println();
            String tableName = resultSet.getString("name");
            System.out.println("表名称 = "+tableName);
            String tableComment = resultSet.getString("COMMENT");
            System.out.println("表注释"+tableComment);
            System.out.println("***************");
            System.out.println("***************");
            StringBuilder tableSql = new StringBuilder("show full fields from ");
            tableSql.append(tableName);
            PreparedStatement ps = connection.prepareStatement(tableSql.toString());
            ResultSet results = ps.executeQuery();
                while (results.next()) {
                    String columnName = results.getString("FIELD");
                    String type = results.getString("TYPE");
                    String comment = results.getString("COMMENT");
                    System.out.println("字段名称="+columnName+"字段类型="+type+"字段注释="+comment);
                }
        }
       /* //获得元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获得表信息
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

        while (tables.next()) {
            Map<String, String> columnNameMap = new HashMap<>(); //保存字段名
            //获得表名
            String table_name = tables.getString("TABLE_NAME");
            //通过表名获得所有字段名
            ResultSet columns = metaData.getColumns(null, null, table_name, "%");
            //获得所有字段名
            while (columns.next()) {
                //获得字段名
                String column_name = columns.getString("COLUMN_NAME");
                //获得字段类型
                String type_name = columns.getString("TYPE_NAME");

                columnNameMap.put(column_name, type_name);
            }
            tableNameMap.put(table_name, columnNameMap);

        }*/
        return tableNameMap;
    }


    /**
     * 格式化数据库注释内容
     *
     * @param comment 注释
     * @return 注释
     * @since 3.4.0
     */
    public String formatComment(String comment) {
        return StringUtils.isBlank(comment) ? StringPool.EMPTY : comment.replaceAll("\r\n", "\t");
    }


}

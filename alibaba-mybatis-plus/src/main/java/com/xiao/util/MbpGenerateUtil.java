package com.xiao.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

/**
 * @author xiao
 */
public class MbpGenerateUtil {

    /**
     * 数据库连接URL
     */
    private static final String URL = "jdbc:mysql://192.168.44.77:3306/v3_alibaba?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";

    /**
     * 数据库连接账号
     */
    private static final String USERNAME = "root";

    /**
     * 数据库连接密码
     */
    private static final String PASSWORD = "root";

    /**
     * 作者：用在文档注释的 `@author` 中
     */
    private static final String AUTHOR = "xiao";

    /**
     * 文件生成位置
     */
    private static final String OUTPUT_DIR =
            "D:\\Workspace\\JavaSpace\\alibaba-mybatis-plus\\" +
                    "src\\main\\java";

    /**
     * 包名
     */
    private static final String PARENT_PACKAGE = "com.xiao.mbp";

    /**
     * 相关表：使用逗号分割
     */
    private static final String TABLES =
            "product,order,member,role,permission,member_role,role_permission";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> builder.author(AUTHOR).dateType(DateType.ONLY_DATE).outputDir(OUTPUT_DIR).disableOpenDir())
                .packageConfig(builder -> builder.parent(PARENT_PACKAGE))
                .strategyConfig(builder -> builder.addInclude(TABLES))
                .strategyConfig(builder -> builder.serviceBuilder().formatServiceFileName("%sService"))
                .strategyConfig(builder -> builder.entityBuilder().enableLombok())
                .strategyConfig(builder -> builder.controllerBuilder().enableRestStyle().enableHyphenStyle())
                .execute();
        System.out.println("生成完毕");
    }
}

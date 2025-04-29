package com.pethome.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.sql.Types;
import java.util.Collections;

/**
 * @author ：李冠良
 * @description ：MybatisPlus代码生成器
 * @date ：2025 4月 27 15:12
 */


public class MybatisPlusCodeGenerator {
    public static void main(String[] args) {
        System.out.println("MybatisPlus代码生成器启动");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/pet_home", "root", "12345678")
                .globalConfig(builder -> {
                    builder.author("lgl") // 设置作者
                            .outputDir("C:\\Users\\12145\\Desktop\\MybatisPlus生成代码\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.pethome") // 设置父包名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\12145\\Desktop\\MybatisPlus生成代码\\src\\main\\resources\\com\\pethome\\mapper")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder -> builder.serviceBuilder().formatServiceFileName("%sService"))
//                .strategyConfig(builder ->
//                        builder.addInclude("t_simple") // 设置需要生成的表名
//                                .addTablePrefix("t_", "c_") // 设置过滤表前缀
//                )
                .execute();
    }
}
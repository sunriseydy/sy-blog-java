package dev.sunriseydy.blog;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import dev.sunriseydy.blog.common.constants.DateTimeConstant;

/**
 * @author SunriseYDY
 * @date 2022-04-28 12:56
 */
public class MybatisPlusGenerateTests {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8&useSSL=false&useTimezone=true&serverTimezone=Asia/Shanghai",
                        "root", "root")
        ).globalConfig(builder -> builder.author("SunriseYDY").commentDate(DateTimeConstant.DATE_TIME).fileOverride().outputDir("/tmp/java"))
                .packageConfig(builder -> builder.parent("dev.sunriseydy.blog.db.wordpress"))
                .templateConfig(builder -> builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICEIMPL))
                .strategyConfig(builder -> builder.addInclude("wp_commentmeta", "wp_comments", "wp_options", "wp_postmeta", "wp_posts", "wp_term_taxonomy", "wp_termmeta", "wp_terms",
                                "wp_term_relationships", "wp_usermeta", "wp_users")
                        .entityBuilder().enableColumnConstant().enableLombok().enableTableFieldAnnotation().build()
                ).execute();
        FastAutoGenerator.create(
                new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/typecho?useUnicode=true&characterEncoding=utf-8&useSSL=false&useTimezone=true&serverTimezone=Asia/Shanghai",
                        "root", "root")
        ).globalConfig(builder -> builder.author("SunriseYDY").commentDate(DateTimeConstant.DATE_TIME).fileOverride().outputDir("/tmp/java"))
                .packageConfig(builder -> builder.parent("dev.sunriseydy.blog.db.typecho"))
                .templateConfig(builder -> builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICEIMPL))
                .strategyConfig(builder -> builder
                        .entityBuilder().enableColumnConstant().enableLombok().enableTableFieldAnnotation().build()
                ).execute();
    }
}

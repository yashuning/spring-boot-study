package com.nys.study.spring.springbootstudy.dao.plugin.mybatisgenerator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2023/9/8 2:38 PM
 */
public class MybatisGeneratorMain {

    public static void main(String[] args) {
        // 执行中的异常信息会保存在warnings中
        List<String> warnings = new ArrayList<String>();
        try {
            // true:生成的文件覆盖之前的
            boolean overwrite = true;
            // 读取配置,构造 Configuration 对象.
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(
                    MyCommentGenerator.class.getResourceAsStream("/mybatis-generator.xml"));
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

}

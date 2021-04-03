package cn.it;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luis
 */
@SpringBootApplication
@MapperScan("cn.it.mapper")
public class ForestBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForestBlogApplication.class, args);
    }
}

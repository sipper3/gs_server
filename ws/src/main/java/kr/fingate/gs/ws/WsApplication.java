package kr.fingate.gs.ws;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class
        , DataSourceAutoConfiguration.class
//        , MybatisAutoConfiguration.class
})
@ComponentScan(basePackages = {"kr.fingate.gs"})

public class WsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder ab) {
        return ab.sources(WsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }
}

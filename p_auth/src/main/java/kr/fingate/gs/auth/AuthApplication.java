package kr.fingate.gs.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
        , DataSourceTransactionManagerAutoConfiguration.class
        , DataSourceAutoConfiguration.class
})
@ComponentScan(basePackages = {"kr.fingate.gs"})
public class AuthApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder ab) {
        return ab.sources(AuthApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AuthApplication.class);
        app.addListeners(new ApplicationPidFileWriter()); // ApplicationPidFileWriter 설정
        app.run(args);
    }


}
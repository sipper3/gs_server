package kr.fingate.gs.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestYml {
    @Value("${server.name-core}")
    private String address;

    @Value("${server.name}")
    private String name;

    @Value("${server.name2}")
    private String address2;

//    @Value("${springdoc.swagger-ui.path}")
//    private String swaggerPath;


    @Bean
    public void demoTest() {
        System.out.println(address);
        System.out.println(name);
        System.out.println(address2);
//        System.out.println(swaggerPath);
        System.out.println("11111");
    }


}

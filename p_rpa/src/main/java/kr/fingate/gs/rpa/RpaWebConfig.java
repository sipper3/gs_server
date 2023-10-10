package kr.fingate.gs.rpa;

import kr.fingate.gs.core.config.WebConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpaWebConfig extends WebConfig {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*") // 허용할 출처
//                .allowedMethods("GET", "POST", "OPTIONS") // 허용할 HTTP method
//                .allowCredentials(true) // 쿠키 인증 요청 허용
//                .maxAge(3000); // 원하는 시간만큼 pre-flight request를 캐싱
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOriginPattern("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod(HttpMethod.GET);
//        config.addAllowedMethod(HttpMethod.POST);
//        config.addAllowedMethod(HttpMethod.OPTIONS);
//        config.setMaxAge(3000L);
//
//        source.registerCorsConfiguration("/**", config) ;
//        return new CorsFilter(source);
//    }
}
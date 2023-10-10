package kr.fingate.gs.core.security;

import kr.fingate.gs.core.security.filters.JwtAuthmFilter;
import kr.fingate.gs.core.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;


/*
 * 참고 : Filter와 permitAll의 관계
 *
 * permitAll()은 해당 요청에 대한 접근을 모든 사용자에게 허용하는 역할을 하지만, 요청 처리 과정에서 적용되는 모든 필터들을 무시하지는 않는다.
 * 단지 해당 요청에 대한 인증 정보가 없더라도 (모든 필터를 처리한 후에도 SecurityContext에 인증 정보가 없더라도), 접근이 허용된다는 것을 의미한다.
 * 따라서 filter 구성 중 특정 조건에서 Exception을 던지는 부분이 있다면, permitALl()을 한 것과는 상관 없이 Exception이 잡히는 것에 유의
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${project.name}")
    private String PROJECT_NAME;

    @Value("${jwt.request.ignore.core:}")
    private String[] JWT_REQUEST_IGNORE_CORE;

    @Value("${jwt.request.ignore.module:}")
    private String[] JWT_REQUEST_IGNORE_MODULE;

    @Value("${jwt.request.permit.core:}")
    private String[] JWT_REQUEST_PERMIT_CORE;

    @Value("${jwt.request.permit.module:}")
    private String[] JWT_REQUEST_PERMIT_MODULE;

    protected final JwtService jwtService;

    /*
     * Filter를 거치지 않는 api url 정의
     * swagger 접근용 / resource 접근용
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        String[] JWT_REQUEST_IGNORE = Stream.concat(Arrays.stream(JWT_REQUEST_IGNORE_CORE), Arrays.stream(JWT_REQUEST_IGNORE_MODULE))
                                            .toArray(String[]::new);
        return (web) -> web.ignoring()
                .requestMatchers(JWT_REQUEST_IGNORE);
    }

    /*
     * permitAll()은 해당 요청에 대한 접근을 모든 사용자에게 허용할 뿐, Filter를 생략하지는 않는다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        /*
         * Modeule별 securityFilterChain에 속하지 않는 모든 api 유입에 대해 처리
         * 자원을 제외한 모든 api는 인증을 요구한다.
         */
        http.securityMatcher("/**")
                .formLogin(formLogin ->
                        formLogin.disable() // formLogin 사용 X
                )
                .httpBasic(httpBasic ->
                        httpBasic.disable() // httpBasic 사용 X
                )
                .cors(
                        cors -> cors.disable()  // cors 보안 사용 X, 타 도메인에서 API 호출 가능
                )
                .csrf(
                        csrf -> csrf.disable()  // csrf 보안 사용 X
                )
                .headers(headers -> // X-frame-Options 사용 X
                        headers.frameOptions(options ->
                                options.disable()
                        )
                )
                // 세션 사용하지 않으므로 STATELESS로 설정
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 인증 예외 url 등록
                .authorizeHttpRequests(request -> {
                    String[] JWT_REQUEST_PERMIT = Stream.concat(Arrays.stream(JWT_REQUEST_PERMIT_CORE), Arrays.stream(JWT_REQUEST_PERMIT_MODULE))
                            .toArray(String[]::new);


                    if(Array.getLength(JWT_REQUEST_PERMIT) > 0){
                        request.requestMatchers(JWT_REQUEST_PERMIT).permitAll()
                                .anyRequest().authenticated();
                    } else {
//                    String projectApi = String.format("/%s/api/*", PROJECT_NAME);
//                    request.requestMatchers(projectApi).authenticated();
                        request.anyRequest().authenticated();
                    }

                    request.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
                });

        /* Filter 순서 정의
         * Filter의 순서를 정해주지 않으면 runtime Exception 발생으로 서버 구동이 불가하므로
         * 각 Module별 Filter를 추가한다면 Order를 정의해줘야한다.
         *
         * 순서 : LogoutFilter -> (Module Custom Filter) -> JwtAuthmFilter -> LoginAuthmFilter
         */

        http.addFilterAfter(jwtAuthmFilter(), LogoutFilter.class); // jwt Filer 정의 (필수);
        return http.build();
    }

    public JwtAuthmFilter jwtAuthmFilter() {
        JwtAuthmFilter jwtAuthorizationFilter = new JwtAuthmFilter(jwtService);
        return jwtAuthorizationFilter;
    }
}

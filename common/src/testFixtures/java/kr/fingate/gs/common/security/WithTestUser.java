package kr.fingate.gs.common.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithTestUserSecurityContextFactory.class)
public @interface WithTestUser {

    String username() default "tester";
    String grade() default "USER";

}

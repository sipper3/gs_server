package kr.fingate.gs.common.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WithTestUserSecurityContextFactory implements WithSecurityContextFactory<WithTestUser> {

    @Override
    public SecurityContext createSecurityContext(WithTestUser annotation) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Map<String, Object> userTokenMap = new HashMap<>();
        userTokenMap.put("clientNo", 100000L);
        userTokenMap.put("userNo", 0);
        userTokenMap.put("userName", annotation.username());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userTokenMap, "", Collections.singleton(new SimpleGrantedAuthority(annotation.grade())));
        securityContext.setAuthentication(authenticationToken);

        return securityContext;
    }
}

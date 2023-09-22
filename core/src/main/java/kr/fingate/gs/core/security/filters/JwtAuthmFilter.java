package kr.fingate.gs.core.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.core.aop.response.ResponseCode;
import kr.fingate.gs.core.aop.response.RestResponse;
import kr.fingate.gs.core.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtAuthmFilter extends GenericFilterBean {

    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = "";

        try {
            // TODO 인증 작업 후 수정
//            accessToken = jwtService.extractAccessToken((HttpServletRequest)request).orElse("");
            accessToken = jwtService.createSystemToken();
            
            // Token이 있는 경우에만 체크
            if(!StringUtils.isEmpty(accessToken)) {
                // 만료된 Token은 사용 불가 /  Exception 처리
                if(jwtService.isTokenValid(accessToken)){
                    throw new Exception("비정상 접근");
                } else {
                    // 유효한 Token을 이용하여 authentication 인증 객체 생성 후 다음 filter 진행
                    jwtService.saveAuthentication(accessToken);
                }
            }

            filterChain.doFilter(request, response);
        }
        // TODO - Error 정의 필요
        catch (SignatureException | MalformedJwtException e) {
            wrappingOut((HttpServletResponse)response, ResponseCode.FAIL, "SignatureException error");
        } catch (ExpiredJwtException e) {
            wrappingOut((HttpServletResponse)response, ResponseCode.FAIL, "ExpiredJwtException error");
        } catch (IllegalArgumentException e) {
            wrappingOut((HttpServletResponse)response, ResponseCode.FAIL, "JWT String argument cannot be null or empty");
        } catch(Exception e) {
            e.printStackTrace();
            ((HttpServletResponse)response).sendError(500, e.toString());
        }
    }

    private void wrappingOut(HttpServletResponse response, ResponseCode code, String message) {
        PrintWriter printWriter;
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(code.value());
    
            ObjectMapper objectMapper = new ObjectMapper();
    
            // TODO - Error 정의 필요
            RestResponse<Object> outDto = new RestResponse<Object>(code, null, null);
            String json = objectMapper.writeValueAsString(outDto);
    
            printWriter = response.getWriter();
            printWriter.write(json);
            printWriter.flush();   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package kr.fingate.gs.core.security.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import kr.fingate.gs.core.consts.enums.AuthmRole;
import kr.fingate.gs.core.consts.enums.TokenType;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private Key secretKeyCode;

    private final long CLIENT_NO = 100000L;
    private final long SYSTEM_NO = 1L;

    @PostConstruct
    public void createSecretKey() {
        // KeySpec를 사용하여 키 생성
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKeyInit = new SecretKeySpec(keyBytes, "HmacSHA512");
        this.secretKeyCode = Keys.hmacShaKeyFor(secretKeyInit.getEncoded());
    }

    /**
     * System용 AccessToken 생성 메서드
     * Token의 유효 시간은 3분
     */
    public String createSystemToken() {
        // clientNo 별 Token에 저장할 값 로딩
        Claims claims = Jwts.claims();
        claims.put("clientNo", CLIENT_NO);
        claims.put("userNo", SYSTEM_NO);
        claims.put("userName", "시스템");

        return Jwts.builder()
                // 헤더의 타입(typ)을 jwt 설정
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(TokenType.ACCESS_TOKEN.getSubject())
                .setClaims(claims) // 데이터
                .setIssuedAt(new Date()) // 토큰 발행일
                .setExpiration(getSystemExpireDate()) // set Expire Time
                .signWith(this.secretKeyCode, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * AccessToken 생성 메서드
     * ClientNo 별 AccessToken에 저장되는 데이터는 각각 괸리한다.
     * 모든 값은 String으로 저장
     */
    public String createAccessToken(UserTokenDto userTokenDto) {
        // clientNo 별 Token에 저장할 값 로딩
        Claims claims = getClaimsFromUser(userTokenDto);

        return Jwts.builder()
                // 헤더의 타입(typ)을 jwt 설정
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(TokenType.ACCESS_TOKEN.getSubject())
                .setClaims(claims) // 데이터
                .setIssuedAt(new Date()) // 토큰 발행일
                .setExpiration(getAccessExpireDate()) // set Expire Time
                .signWith(this.secretKeyCode, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * RefreshToken 생성 메서드
     * RefreshToken은 오로지 userNo만 저장한다.
     * 모든 값은 String으로 저장
     */
    public String createRefreshToken(long userNo) {
        Claims claims = Jwts.claims();
        claims.put("userNo", userNo);

        return Jwts.builder()
                // 헤더의 타입(typ)을 jwt 설정
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(TokenType.REFRESH_TOKEN.getSubject())
                .setClaims(claims) // 데이터
                .setIssuedAt(new Date()) // 토큰 발행일
                .setExpiration(getRefreshExpireDate()) // set Expire Time
                .signWith(this.secretKeyCode, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Header에서 AccessToken 추출
     */
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(TokenType.ACCESS_TOKEN.getHeader()));
    }

    /**
     * token의 만료 검증 (access or refresh)
     * true : 만료, false : 유효
     */
    public boolean isTokenValid(String token) {
        boolean valid = StringUtils.isEmpty(token);
        valid |= getClaims(token)
                .getBody()
                .getExpiration()
                .before(new Date());

        return valid;
    }

    public void saveAuthentication(String accessToken) {
        Authentication authentication = this.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Authentication getAuthentication(String accessToken) {
        UserTokenDto userTokenDto = this.parseTokenUser(accessToken);
        return new UsernamePasswordAuthenticationToken(userTokenDto, "", Collections.singleton(new SimpleGrantedAuthority(AuthmRole.USER.getKey())));
    }

    /**
     * token to UserTokenDto
     */
    public UserTokenDto parseTokenUser(String token) {
        return parseTokenUser(getClaims(token));
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKeyCode)
                .build()
                .parseClaimsJws(token);
    }

    public UserTokenDto parseTokenUser(Jws<Claims> claims) {
        return new ObjectMapper()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // 알 수 없는 속성이 있어도 실패 처리하지 않음
                    .convertValue(claims.getBody(), new TypeReference<>() {});
    }

    public Claims getClaimsFromUser(UserTokenDto userTokenDto) {
        Claims claims = Jwts.claims();
        try {
            for (Field field : FieldUtils.getAllFields(UserTokenDto.class)) {
                String fieldName = field.getName();
                field.setAccessible(true);
                Object data = field.get(userTokenDto);

                claims.put(fieldName, String.valueOf(data));
                field.setAccessible(false);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return claims;
    }

    // 인증 토큰 만료일자 구하기
    public static Date getAccessExpireDate() {
        return getRefreshExpireDate();
    }

    // 리프레쉬 토큰 만료일자 구하기
    public static Date getRefreshExpireDate() {
        Date nowDate = new Date();

        // 만료일자 - 생성일자 23시 59분 59초
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    // 시스템 인증 토큰 만료일자 구하기
    public static Date getSystemExpireDate() {
        Date nowDate = new Date();

        // 만료일자 - 생성일자로부터 5분 뒤
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.MINUTE, 3);
        return calendar.getTime();
    }
}
